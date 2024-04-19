/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.service;

import com.xushicao.accounting.facade.req.AccountReq;

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
}