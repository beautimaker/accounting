/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.service.Impl;

import com.xushicao.accounting.dao.entity.AccountDO;
import com.xushicao.accounting.dao.entity.AccountLogDO;
import com.xushicao.accounting.dao.entity.TransLogDO;
import com.xushicao.accounting.dao.mapper.AccountLogMapper;
import com.xushicao.accounting.dao.mapper.AccountMapper;
import com.xushicao.accounting.dao.mapper.SequenceMapper;
import com.xushicao.accounting.dao.mapper.TransLogMapper;
import com.xushicao.accounting.facade.req.DepositReq;
import com.xushicao.accounting.facade.req.TransferReq;
import com.xushicao.accounting.facade.req.WithdrawReq;
import com.xushicao.accounting.model.Account;
import com.xushicao.accounting.model.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * 通用服务基类
 *
 * @author Shichao.xu
 * @version $ BaseService, V0.1 2024/4/16 9:05 Shichao.xu Exp $
 */
@Service
public class BaseService {

    protected static final Logger LOGGER = LoggerFactory.getLogger(BaseService.class);

    /**
     * 用户实体对象
     */
    @Autowired
    AccountMapper accountMapper;

    /**
     * 事务模板对象
     */
    @Autowired
    TransactionTemplate transactionTemplate;

    /**
     * 序列号映射对象
     */
    @Autowired
    SequenceMapper sequenceMapper;

    /**
     * 交易记录映射对象
     */
    @Autowired
    TransLogMapper transLogMapper;

    /**
     * 账户变动映射对象
     */
    @Autowired
    AccountLogMapper accountLogMapper;

    /**
     * 交易信息内部类
     */
    public class TransInfo {
        /**
         * 借方对应账户账号
         */
        private String debitAccountNo;

        /**
         * 贷方账户账号
         */
        private String creditAccountNo;


        /**
         * 交易金额
         */
        private long amount;


        /**
         * 操作员ID
         */
        private String operatorID;

        /**
         * 订单号
         */
        private String orderNo;

        /**
         * 核算机构
         */
        private String reconInst;

        /**
         * 交易日期
         */
        private LocalDate transDate;

        /**
         * 交易时间
         */
        private LocalDateTime transDT;

        /**
         * 交易代码
         */
        private String transCode;

        /**
         * 交易子代码
         */
        private String subTransCode;

        /**
         * 外部业务时间
         */
        private LocalDateTime outDate;

        /**
         * 交易币种
         */
        private String currency;


        private String ID;

        public TransInfo(DepositReq depositReq) {
            this.creditAccountNo = depositReq.getAccountNo();
            this.debitAccountNo = matchTotalAccount(depositReq.getAccountNo());
            this.amount = depositReq.getAmount();
            this.operatorID = depositReq.getOperatorID();
            this.orderNo = depositReq.getOrderNo();
            this.reconInst = depositReq.getReconInst();
            if (depositReq.getTransDT() != null) {
                this.transDT = depositReq.getTransDT();
            } else {
                this.transDT = accountMapper.selectNow();
            }
            this.transCode = depositReq.getTransCode();
            this.subTransCode = depositReq.getSubTransCode();
            this.outDate = depositReq.getOutDate();
            this.currency = depositReq.getCurrency();
        }

        public TransInfo(WithdrawReq withdrawReq) {
            this.creditAccountNo = matchTotalAccount(withdrawReq.getAccountNo());
            this.debitAccountNo = withdrawReq.getAccountNo();
            this.amount = withdrawReq.getAmount();
            this.operatorID = withdrawReq.getOperatorID();
            this.orderNo = withdrawReq.getOrderNo();
            this.reconInst = withdrawReq.getAccountNo();
            if (withdrawReq.getTransDT() != null) {
                this.transDT = withdrawReq.getTransDT();
            } else {
                this.transDT = accountMapper.selectNow();
            }
            this.transCode = withdrawReq.getTransCode();
            this.subTransCode = withdrawReq.getSubTransCode();
            this.outDate = withdrawReq.getOutDate();
            this.currency = withdrawReq.getCurrency();
        }

        public TransInfo(TransferReq transferReq) {
            this.debitAccountNo = transferReq.getAccountFromNo();
            this.creditAccountNo = transferReq.getAccountToNo();
            this.amount = transferReq.getAmount();
            this.operatorID = transferReq.getOperatorID();
            this.orderNo = transferReq.getOrderNo();
            this.reconInst = transferReq.getReconInst();
            if (transferReq.getTransDT() != null) {
                this.transDT = transferReq.getTransDT();
            } else {
                this.transDT = accountMapper.selectNow();
            }
            this.transCode = transferReq.getTransCode();
            this.subTransCode = transferReq.getSubTransCode();
            this.outDate = transferReq.getOutDate();
            this.currency = transferReq.getCurrency();
        }


        public String getDebitAccountNo() {
            return debitAccountNo;
        }

        public void setDebitAccountNo(String debitAccountNo) {
            this.debitAccountNo = debitAccountNo;
        }

        public String getCreditAccountNo() {
            return creditAccountNo;
        }

        public void setCreditAccountNo(String creditAccountNo) {
            this.creditAccountNo = creditAccountNo;
        }

        public long getAmount() {
            return amount;
        }

        public void setAmount(long amount) {
            this.amount = amount;
        }

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }
    }


    /**
     * 交易服务方法
     */
    public void transService(TransInfo transInfo) {
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {

                //排序加锁以及生成实体对象
                AccountDO[] accountDOs = buildAccountDOs(transInfo);
                AccountDO creditAccountDO = accountDOs[0];
                AccountDO debitAccountDO = accountDOs[1];


                //生成账户工厂
                Factory accountFactory = new Factory();

                //贷方对应账户
                Account creditAccount = accountFactory.create(creditAccountDO.getDirection(), creditAccountDO.getAccountNo()
                        , creditAccountDO.getBalance(), transInfo.transDT, creditAccountDO.getLastTransTime());

                //借方对应账户
                Account debitAccount = accountFactory.create(debitAccountDO.getDirection(), debitAccountDO.getAccountNo(),
                        debitAccountDO.getBalance(), transInfo.transDT, debitAccountDO.getLastTransTime());

                //实现借贷双方对应账户操作
                creditAccount.credit(transInfo.amount);
                debitAccount.debit(transInfo.amount);

                //更新数据库
                long daysCredit = creditAccount.getTransDT().until(creditAccount.getLastTransTime(), ChronoUnit.DAYS);
                long daysDebit = debitAccount.getTransDT().until(debitAccount.getLastTransTime(), ChronoUnit.DAYS);
                accountMapper.updateBalance(creditAccount.getAccountNo(), creditAccount.getBalance(), creditAccount.
                        getTransDT(), daysCredit);
                accountMapper.updateBalance(debitAccount.getAccountNo(), debitAccount.getBalance(), debitAccount.
                        getTransDT(), daysDebit);


                //生成交易记录和账户变动记录
                transLogMapper.insert(buildTransLog(transInfo));
                accountLogMapper.insert(buildAccountLog(creditAccount, transInfo));
                accountLogMapper.insert(buildAccountLog(debitAccount, transInfo));

                transInfo.ID = transLogMapper.select(transInfo.orderNo);
            }
        });
    }

    /**
     * 匹配总账总账账户
     */
    protected String matchTotalAccount(String accountNo) {
        String accountType = accountNo.substring(4, 6);
        if (accountType.equals("01"))
            return "20000310001156";
        else
            return "20000310002156";

    }


    /**
     * 取款变动记录实体对象
     *
     * @param account   业务账户对象
     * @param transInfo 交易信息对象
     * @return 账户实体对象
     */
    private AccountLogDO buildAccountLog(Account account, TransInfo transInfo) {
        AccountLogDO accountLogDO = new AccountLogDO();
        String accountNo = account.getAccountNo();
        String otherAccountNo = (accountNo.equals(transInfo.getCreditAccountNo()) ? transInfo.debitAccountNo :
                transInfo.creditAccountNo);

        accountLogDO.setAccountNo(accountNo);
        accountLogDO.setOtherAccountNo(otherAccountNo);
        accountLogDO.setTransAmount(transInfo.amount);
        accountLogDO.setTransCode(transInfo.transCode);
        accountLogDO.setSubTransCode(transInfo.subTransCode);
        accountLogDO.setReconInst(transInfo.reconInst);
        accountLogDO.setOperatorID(transInfo.operatorID);
        accountLogDO.setOrderNo(transInfo.orderNo);
        accountLogDO.setId(Long.toString(sequenceMapper.getNextVal("translog_seq")));
        accountLogDO.setCurrency(transInfo.currency);
        accountLogDO.setBalance(account.getBalance());
        if (transInfo.transDT == null) {
            accountLogDO.setCondition(true);
        } else {
            accountLogDO.setCondition(false);
            accountLogDO.setTransDT(transInfo.transDT);
        }
        accountLogDO.setOutDate(transInfo.outDate);

        return accountLogDO;
    }

    /**
     * 生成取款交易记录实体对象
     *
     * @param transInfo 交易信息对象
     * @return 交易记录实体对象
     */
    private TransLogDO buildTransLog(TransInfo transInfo) {
        TransLogDO transLogDO = new TransLogDO();
        transLogDO.setDebitAccountNo(transInfo.debitAccountNo);
        transLogDO.setCreditAccountNo(transInfo.getCreditAccountNo());
        transLogDO.setTransAmount(transInfo.amount);
        transLogDO.setTransCode(transInfo.transCode);
        transLogDO.setSubTransCode(transInfo.subTransCode);
        transLogDO.setReconInst(transInfo.reconInst);
        transLogDO.setOperatorID(transInfo.operatorID);
        transLogDO.setOrderNo(transInfo.orderNo);
        transLogDO.setId(Long.toString(sequenceMapper.getNextVal("translog_seq")));
        transLogDO.setCurrency(transInfo.currency);
        if (transInfo.transDT == null) {
            transLogDO.setCondition(true);
        } else {
            transLogDO.setCondition(false);
            transLogDO.setTransDT(transInfo.transDT);
        }
        transLogDO.setOutDate(transInfo.outDate);

        return transLogDO;
    }

    /**
     * 生成账户实体类
     *
     * @param transInfo 交易信息
     * @return 账户实体数组
     */
    private AccountDO[] buildAccountDOs(TransInfo transInfo) {
        String accountCreditSeq = transInfo.creditAccountNo.substring(6, 11);
        String accountDebitSeq = transInfo.debitAccountNo.substring(6, 11);


        AccountDO[] accountDOs = new AccountDO[2];
        if (Integer.parseInt(accountCreditSeq) < Integer.parseInt(accountDebitSeq)) {
            accountDOs[0] = accountMapper.selectForUpdate(transInfo.getCreditAccountNo());
            accountDOs[1] = accountMapper.selectForUpdate(transInfo.getDebitAccountNo());
        } else {
            accountDOs[1] = accountMapper.selectForUpdate(transInfo.getDebitAccountNo());
            accountDOs[0] = accountMapper.selectForUpdate(transInfo.getCreditAccountNo());
        }
        return accountDOs;
    }
}