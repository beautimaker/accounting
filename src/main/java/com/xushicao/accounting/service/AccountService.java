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
     *
     * @param accountReq
     */
    String openAccount(AccountReq accountReq) throws SQLException;

    /**
     * 冻结账户方法
     *
     * @param accountReq 账户请求
     */
    void freezeAccount(AccountReq accountReq);


    void unFreezeAccount(AccountReq accountReq);

}