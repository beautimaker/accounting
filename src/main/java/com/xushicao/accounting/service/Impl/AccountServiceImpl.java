/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.service.Impl;

import com.xushicao.accounting.dao.entity.*;
import com.xushicao.accounting.dao.mapper.*;
import com.xushicao.accounting.facade.req.AccountManageReq;
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


/**
 * @author Shichao.xu
 * @version $ AccountServiceImpl, V0.1 2024/4/16 9:04 Shichao.xu Exp $
 */
@Service
public class AccountServiceImpl extends BaseService implements AccountService {

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
     * @param accountManageReq 用户请求
     * @return 返回结果
     */
    @Override
    public String openAccount(AccountManageReq accountManageReq) {

        //打印日志
        LOGGER.info("收到用户新增开户请求:{}", accountManageReq);

        // 2、生成账号
        String accountNo = getAccountNo(accountManageReq);

        //创建数据对象

        AccountDO accountDO = buildAccount(accountNo, accountManageReq);

        //执行数据库操作
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {

                if (accountManageReq.getAccountType().equals("03")) {
                    InnerAccountInfoDO innerAccountInfoDO = buildInnerAccountInfo(accountNo, accountManageReq);
                    innerAccountInfoMapper.insert(innerAccountInfoDO);
                }
                accountMapper.insert(accountDO);
            }
        });
        LOGGER.info("用户开户请求处理结束:{}", accountManageReq);
        return accountNo;


    }

    /**
     * 冻结账户实现方法
     *
     * @param accountNo 账户账号
     */
    @Override
    public void freezeAccount(String accountNo) {
        //打印日志
        LOGGER.info("收到用户冻结账户请求:{}", accountNo);
        if (accountMapper.select(accountNo).getStatus().equals("F")) {
            throw new AccountingException(AccountingErrDtlEnum.REQ_PARAM_NOT_VALID, "账户已处于冻结状态");
        }

        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {

                if (accountMapper.update(accountNo, "F") != 1) {

                    throw new AccountingException(AccountingErrDtlEnum.DB_EXCEPTION, "修改数据条数不对应");
                }
                accountChangeLogMapper.insert(buildAccountChangeLog(accountNo,
                        "status", "N", "F"));
            }
        });

        LOGGER.info("处理用户冻结请求请求结束:{}", accountNo);
    }

    /**
     * 解冻在账户实现方法
     *
     * @param accountNo 客户请求
     */
    @Override
    public void unFreezeAccount(String accountNo) {

        LOGGER.info("收到用户解冻账户请求:{}", accountNo);

        if (accountMapper.select(accountNo).getStatus().equals("N")) {
            throw new AccountingException(AccountingErrDtlEnum.REQ_PARAM_NOT_VALID, "账户已处于解冻状态");
        }

        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {


                if (accountMapper.update(accountNo, "N") != 1) {
                    throw new AccountingException(AccountingErrDtlEnum.DB_EXCEPTION, "修改数据条数不对应");
                }

                accountChangeLogMapper.insert(buildAccountChangeLog(accountNo, "status",
                        "F", "N"));
            }
        });
        LOGGER.info("处理用户解冻请求请求结束:{}", accountNo);
    }

    /**
     * 账号生成方法
     * 根据客户请求，生活对应账号
     *
     * @param accountManageReq 客户请求
     * @return 账号
     */
    private String getAccountNo(AccountManageReq accountManageReq) {

        String accountType = accountManageReq.getAccountType();//获取账号类型
        String accountCurrency = accountManageReq.getCurrency();//获取账号币种
        String serialNo = Long.toString(sequenceMapper.getNextVal("account_seq")); //获取序列号sequenceMapper.getNextVal("my_sequence")
        String accountNo = "2000" + accountType + serialNo + accountCurrency;
        return accountNo;

    }

    /**
     * 账户实体生成方法
     * 构建账户实体对象
     *
     * @param accountNo        账号对象
     * @param accountManageReq 用户请求对象
     * @return 账户实体对象
     */
    private AccountDO buildAccount(String accountNo, AccountManageReq accountManageReq) {

        AccountDO accountdo = new AccountDO();
        accountdo.setAccountNo(accountNo);
        accountdo.setAccountName(accountManageReq.getAccountName());
        accountdo.setAccountType(accountManageReq.getAccountType());
        accountdo.setStatus("N");
        accountdo.setBalance(0);
        accountdo.setCurrency(accountManageReq.getCurrency());
        return accountdo;

    }

    /**
     * 内部户扩展表建立方法
     * 通过传入参数建立内部户扩展表实体类
     *
     * @param accountNo        账号
     * @param accountManageReq 开户请求对象
     * @return 内部户扩展表实体类
     */
    private InnerAccountInfoDO buildInnerAccountInfo(String accountNo, AccountManageReq accountManageReq) {

        InnerAccountInfoDO innerAccountInfoDO = new InnerAccountInfoDO();
        innerAccountInfoDO.setTitleCode(accountManageReq.getTitleCode());
        innerAccountInfoDO.setRelationInstId(accountManageReq.getRelationInstId());
        innerAccountInfoDO.setRelationCode(accountManageReq.getRelationCode());
        innerAccountInfoDO.setReconInst(accountManageReq.getReconInst());
        innerAccountInfoDO.setAccountNo(accountNo);

        return innerAccountInfoDO;
    }

    /**
     * 创建账户修改对象方法
     *
     * @param accountNo  账号
     * @param changeType 修改字段
     * @param before     之前状态
     * @param after      之后状态
     * @return 账户修改表对象
     */
    private AccountChangeLogDO buildAccountChangeLog(String accountNo, String changeType, String before, String after) {

        AccountChangeLogDO accountChangeLogDO = new AccountChangeLogDO();
        accountChangeLogDO.setAccountNo(accountNo);
        accountChangeLogDO.setChangeType(changeType);
        accountChangeLogDO.setBefore(before);
        accountChangeLogDO.setAfter(after);

        return accountChangeLogDO;
    }

    /**
     * 销户方法重写
     *
     * @param accountNo 用户请求
     */
    @Override
    public void closeAccount(String accountNo) {

        LOGGER.info("收到用户销户请求{}", accountNo);


        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                AccountDO accountDO = accountMapper.selectForUpdate(accountNo);

                if (accountDO.getBalance() != 0) {
                    throw new AccountingException(AccountingErrDtlEnum.REQ_PARAM_NOT_VALID, "账户存在余额，不能销户");
                } else if (accountDO.getStatus().equals("C")) {
                    throw new AccountingException(AccountingErrDtlEnum.REQ_PARAM_NOT_VALID, "账户已销户");
                }

                if (accountMapper.update(accountNo, "C") != 1) {
                    throw new AccountingException(AccountingErrDtlEnum.DB_EXCEPTION, "修改数据条数不对应");
                }
                accountChangeLogMapper.insert(buildAccountChangeLog(accountNo, "status",
                        accountDO.getStatus(), "C"));
            }
        });


        LOGGER.info("处理用户销户请求结束{}", accountNo);
    }


}
