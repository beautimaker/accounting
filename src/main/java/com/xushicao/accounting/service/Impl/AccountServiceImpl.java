/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.service.Impl;

import com.xushicao.accounting.dao.entity.AccountChangeLogDO;
import com.xushicao.accounting.dao.entity.AccountDO;
import com.xushicao.accounting.dao.entity.InnerAccountInfoDO;
import com.xushicao.accounting.dao.mapper.AccountChangeLogMapper;
import com.xushicao.accounting.dao.mapper.AccountMapper;
import com.xushicao.accounting.dao.mapper.InnerAccountInfoMapper;
import com.xushicao.accounting.dao.mapper.SequenceMapper;
import com.xushicao.accounting.facade.req.AccountReq;
import com.xushicao.accounting.model.enums.AccountingErrDtlEnum;
import com.xushicao.accounting.model.exception.AccountingException;
import com.xushicao.accounting.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.security.PrivateKey;

/**
 * @author Shichao.xu
 * @version $ AccountServiceImpl, V0.1 2024/4/16 9:04 Shichao.xu Exp $
 */
@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);
    /**
     * 开户映射接口
     * 用于实现插入开户数据到mysql
     */
    @Autowired
    private AccountMapper accountMapper;

    /**
     * 序列号映射接口
     * 用于生成序列号
     */
    @Autowired
    private SequenceMapper sequenceMapper;

    /**
     * 内部户扩展表映射接口
     * 用于实现插入内部户扩展表数据到mysql
     */
    @Autowired
    private InnerAccountInfoMapper innerAccountInfoMapper;

    /**
     * 用户修改记录表映射接口对象
     */
    @Autowired
    private AccountChangeLogMapper accountChangeLogMapper;

    /**
     * 事务管理对象
     */
    @Autowired
    private TransactionTemplate transactionTemplate;

    /**
     * 开户方法
     * 完成数据库插入，实现开户
     *
     * @param accountReq
     * @return
     */
    @Override
    public String openAccount(AccountReq accountReq) {

        //打印日志
        LOGGER.info("收到用户新增开户请求:{}", accountReq);

        // 2、生成账号
        String accountNo = getAccountNo(accountReq);

        //创建数据对象

        AccountDO accountDO = buildAccount(accountNo, accountReq);

        //执行数据库操作
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {

                if (accountReq.getAccountType().equals("03")) {
                    InnerAccountInfoDO innerAccountInfoDO = buildInnerAccountInfo(accountNo, accountReq);
                    innerAccountInfoMapper.insert(innerAccountInfoDO);
                }
                accountMapper.insert(accountDO);
            }
        });
        LOGGER.info("用户开户请求处理结束:{}", accountReq);
        return accountNo;


    }

    /**
     * 冻结账户实现方法
     *
     * @param accountReq 账户请求
     */
    @Override
    public void freezeAccount(AccountReq accountReq) {
        //打印日志
        LOGGER.info("收到用户冻结账户请求:{}", accountReq.getAccountNo());

        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                if (accountMapper.checkStatus(accountReq.getAccountNo()).getStatus().equals("F")) {
                    throw new AccountingException(AccountingErrDtlEnum.REQ_PARAM_NOT_VALID, "账户已处于冻结状态");
                } else {
                    accountMapper.freeze(accountReq.getAccountNo());
                    accountChangeLogMapper.change(buildAccountChangeLog(accountReq.getAccountNo(),
                            "status", "N", "F"));
                }
                LOGGER.info("处理用户冻结请求请求结束:{}", accountReq.getAccountNo());
            }
        });

    }

    /**
     * 解冻在账户实现方法
     *
     * @param accountReq 客户请求
     */
    @Override
    public void unFreezeAccount(AccountReq accountReq) {

        LOGGER.info("收到用户解冻账户请求:{}", accountReq.getAccountNo());

        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                if (accountMapper.checkStatus(accountReq.getAccountNo()).equals("N")) {
                    throw new AccountingException(AccountingErrDtlEnum.REQ_PARAM_NOT_VALID, "账户已处于解冻状态");
                } else {
                    accountMapper.unFreeze(accountReq.getAccountNo());
                    accountChangeLogMapper.change(buildAccountChangeLog(accountReq.getAccountNo(), "status",
                            "F", "N"));
                }

                LOGGER.info("处理用户解冻请求请求结束:{}", accountReq.getAccountNo());
            }
        });

    }

    /**
     * 账号生成方法
     * 根据客户请求，生活对应账号
     *
     * @param accountReq 客户请求
     * @return 账号
     */
    private String getAccountNo(AccountReq accountReq) {

        String accountType = accountReq.getAccountType();//获取账号类型
        String accountCurrency = accountReq.getCurrency();//获取账号币种
        String serialNo = Long.toString(sequenceMapper.getNextVal("account_seq")); //获取序列号sequenceMapper.getNextVal("my_sequence")
        String accountNo = "2000" + accountType + serialNo + accountCurrency;
        return accountNo;

    }

    /**
     * 账户实体生成方法
     * 构建账户实体对象
     *
     * @param accountNo  账号对象
     * @param accountReq 用户请求对象
     * @return 账户实体对象
     */
    private AccountDO buildAccount(String accountNo, AccountReq accountReq) {

        AccountDO accountdo = new AccountDO();
        accountdo.setAccountNo(accountNo);
        accountdo.setAccountName(accountReq.getAccountName());
        accountdo.setAccountType(accountReq.getAccountType());
        accountdo.setStatus("N");
        accountdo.setBalance(0);
        accountdo.setCurrency(accountReq.getCurrency());
        return accountdo;

    }

    /**
     * 内部户扩展表建立方法
     * 通过传入参数建立内部户扩展表实体类
     *
     * @param accountNo  账号
     * @param accountReq 开户请求对象
     * @return 内部户扩展表实体类
     */
    private InnerAccountInfoDO buildInnerAccountInfo(String accountNo, AccountReq accountReq) {

        InnerAccountInfoDO innerAccountInfoDO = new InnerAccountInfoDO();
        innerAccountInfoDO.setTitleCode(accountReq.getTitleCode());
        innerAccountInfoDO.setRelationInstId(accountReq.getRelationInstId());
        innerAccountInfoDO.setRelationCode(accountReq.getRelationCode());
        innerAccountInfoDO.setReconInst(accountReq.getReconInst());
        innerAccountInfoDO.setAccountNo(accountNo);

        return innerAccountInfoDO;
    }

    private AccountChangeLogDO buildAccountChangeLog(String accountNo, String changeType, String before, String after) {

        AccountChangeLogDO accountChangeLogDO = new AccountChangeLogDO();
        accountChangeLogDO.setAccountNo(accountNo);
        accountChangeLogDO.setChangeType(changeType);
        accountChangeLogDO.setBefore(before);
        accountChangeLogDO.setAfter(after);

        return accountChangeLogDO;
    }

}