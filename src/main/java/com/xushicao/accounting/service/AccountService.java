/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.service;

import com.xushicao.accounting.dao.entity.AccountDO;
import com.xushicao.accounting.facade.req.AccountReq;
import com.xushicao.accounting.facade.result.AccountResult;

import java.sql.SQLException;


/**
 * 账户服务接口
 *
 * @author Shichao.xu
 * @version $ AccountService, V0.1 2024/4/16 8:51 Shichao.xu Exp $
 */

public interface AccountService {

    /**
     * 开户方法
     * 通过传入请求对象，生成用户账号，
     * 并向数据库中插入数据记录
     *
     * @param accountReq
     */
    String openAccount(AccountReq accountReq);


    /**
     * 冻结账户方法
     * 通过传入账户账号，查询数据库中账户状态是否为N。
     * 修改其状态为F，并在修改表中插入修改记录
     *
     * @param accountNo 请求对象
     */
    void freezeAccount(String accountNo);

    /**
     * 解冻账户方法
     * 通过传入账户账号，查询数据库中账户状态是否为F。
     * 修改其状态为N，并在修改表中插入修改记录
     *
     * @param accountNo 请求对象
     */
    void unFreezeAccount(String accountNo);

    /**
     * 销户方法
     * 通过传入账户账号，查询数据库中账户状态是否不为C
     * 且用户余额是否为0
     * 修改其状态为C，并在修改表中插入修改记录
     *
     * @param accountNo 用户账号
     */
    void closeAccount(String accountNo);

    /**
     * 存款方法
     * 通过传入账户账号，判断账户为个人账户，还是、企业账户
     * 企业账户，根据传入金额，同时向账户和总账账户添加存款
     *
     * @param accountNo 账户账号
     * @param amount    存款金额
     */
    void deposit(String accountNo, long amount);

    /**
     * 取款方法接口
     * 通过传入账户账号，判断账户是个人还是企业账户
     * 根据传入金额，同时向账户和总账账户添加存款
     *
     * @param accountNo
     * @param amount
     */
    void withdraw(String accountNo, long amount);

    /**
     * 转账方法接口
     * 将传入的金额，更新转入账户和转出账户的余额
     *
     * @param accountFromNo 转入账户账号
     * @param accountToNo   转出账户账号
     * @param amount        金额
     */
    void transfer(String accountFromNo, String accountToNo, long amount);

    /**
     * 账户查询方法
     * 通过传入的账号，获取账户实体类，返回账户信息
     *
     * @param accountNo 账号
     * @return 账户实体类
     */
    AccountDO queryAccount(String accountNo);

}