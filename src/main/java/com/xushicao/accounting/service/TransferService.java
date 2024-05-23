/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.service;

import com.xushicao.accounting.facade.req.DepositReq;
import com.xushicao.accounting.facade.req.TransferReq;
import com.xushicao.accounting.service.Impl.BaseService;

/**
 * 转账服务接口
 *
 * @author Shichao.xu
 * @version $ TransferService, V0.1 2024/5/10 21:26 Shichao.xu Exp $
 */
public interface TransferService {

    /**
     * 转账方法接口
     * 将传入的金额，更新转入账户和转出账户的余额
     *
     * @param transferReq 转账请求
     */
    BaseService.TransInfo transfer(TransferReq transferReq);
}