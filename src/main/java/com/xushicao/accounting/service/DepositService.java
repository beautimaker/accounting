/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.service;

import com.xushicao.accounting.facade.req.DepositReq;
import com.xushicao.accounting.service.Impl.BaseService;

/**
 * 存款服务接口
 *
 * @author Shichao.xu
 * @version $ DepositService, V0.1 2024/5/10 21:25 Shichao.xu Exp $
 */
public interface DepositService {

    /**
     * 存款方法
     * 通过传入账户账号，判断账户为个人账户，还是、企业账户
     * 企业账户，根据传入金额，同时向账户和总账账户添加存款
     *
     * @param depositReq 账户账号
     */
    BaseService.TransInfo deposit(DepositReq depositReq);


}