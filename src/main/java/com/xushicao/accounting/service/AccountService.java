/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.service;

import com.xushicao.accounting.dao.entity.AccountDO;
import com.xushicao.accounting.facade.req.OpenAccountReq;

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
     * @param openAccountReq
     */
    String openAccount(OpenAccountReq openAccountReq) throws SQLException;

}