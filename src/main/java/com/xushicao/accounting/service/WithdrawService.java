/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.service;

import com.xushicao.accounting.facade.req.WithdrawReq;

/**
 * 取款服务接口
 *
 * @author Shichao.xu
 * @version $ WithdrawServicw, V0.1 2024/5/10 21:26 Shichao.xu Exp $
 */
public interface WithdrawService {

    /**
     * 取款方法接口
     * 通过传入账户账号，判断账户是个人还是企业账户
     * 根据传入金额，同时向账户和总账账户添加存款
     *
     * @param withdrawReq 交易请求
     */
    void withdraw(WithdrawReq withdrawReq);
}