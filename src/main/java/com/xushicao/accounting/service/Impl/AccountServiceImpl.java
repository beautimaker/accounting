/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.service.Impl;

import com.xushicao.accounting.dao.entity.AccountDO;
import com.xushicao.accounting.dao.entity.InnerAccountInfoDO;
import com.xushicao.accounting.dao.mapper.AccountMapper;
import com.xushicao.accounting.dao.mapper.InnerAccountInfoMapper;
import com.xushicao.accounting.dao.mapper.SequenceMapper;
import com.xushicao.accounting.facade.req.OpenAccountReq;
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
     * 内部会扩展表映射接口
     * 用于实现插入内部户扩展表数据到mysql
     */
    @Autowired
    private InnerAccountInfoMapper innerAccountInfoMapper;


    /**
     * 事务管理对象
     */
    @Autowired
    private TransactionTemplate transactionTemplate;

    /**
     * 开户方法
     * 完成数据库插入，实现开户
     *
     * @param openAccountReq
     * @return
     */
    @Override
    public String openAccount(OpenAccountReq openAccountReq) {

        //打印日志
        LOGGER.info("收到用户新增开户请求:{}", openAccountReq);

        // 2、生成账号
        String accountNo = getAccountNo(openAccountReq);

        //创建数据对象

        AccountDO accountDO = buildAccount(accountNo, openAccountReq);

        //执行数据库操作
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {

                if (openAccountReq.getAccountType().equals("03")) {
                    InnerAccountInfoDO innerAccountInfoDO = buildInnerAccountInfo(accountNo, openAccountReq);
                    innerAccountInfoMapper.insert(innerAccountInfoDO);
                }
                accountMapper.insert(accountDO);
            }
        });
        LOGGER.info("用户开户请求处理结束:{}", openAccountReq);
        return accountNo;


    }

    /**
     * 账号生成方法
     * 根据客户请求，生活对应账号
     *
     * @param openAccountReq 客户请求
     * @return 账号
     */
    public String getAccountNo(OpenAccountReq openAccountReq) {

        String accountType = openAccountReq.getAccountType();//获取账号类型
        String accountCurrency = openAccountReq.getCurrency();//获取账号币种
        String serialNo = Long.toString(sequenceMapper.getNextVal("account_seq")); //获取序列号sequenceMapper.getNextVal("my_sequence")
        String accountNo = "2000" + accountType + serialNo + accountCurrency;
        return accountNo;

    }

    /**
     * 账户实体生成方法
     * 构建账户实体对象
     *
     * @param accountNo      账号对象
     * @param openAccountReq 用户请求对象
     * @return 账户实体对象
     */
    private AccountDO buildAccount(String accountNo, OpenAccountReq openAccountReq) {

        AccountDO accountdo = new AccountDO();
        accountdo.setAccountNo(accountNo);
        accountdo.setAccountName(openAccountReq.getAccountName());
        accountdo.setAccountType(openAccountReq.getAccountType());
        accountdo.setStatus("N");
        accountdo.setBalance(0);
        accountdo.setCurrency(openAccountReq.getCurrency());
        return accountdo;

    }

    /**
     * 内部户扩展表建立方法
     * 通过传入参数建立内部户扩展表实体类
     *
     * @param accountNo      账号
     * @param openAccountReq 开户请求对象
     * @return 内部户扩展表实体类
     */
    private InnerAccountInfoDO buildInnerAccountInfo(String accountNo, OpenAccountReq openAccountReq) {

        InnerAccountInfoDO innerAccountInfoDO = new InnerAccountInfoDO();
        innerAccountInfoDO.setTitleCode(openAccountReq.getTitleCode());
        innerAccountInfoDO.setRelationInstId(openAccountReq.getRelationInstId());
        innerAccountInfoDO.setRelationCode(openAccountReq.getRelationCode());
        innerAccountInfoDO.setReconInst(openAccountReq.getReconInst());
        innerAccountInfoDO.setAccountNo(accountNo);

        return innerAccountInfoDO;
    }


}