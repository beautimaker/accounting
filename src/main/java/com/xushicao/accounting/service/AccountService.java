/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.service;

import com.xushicao.accounting.dao.entity.AccountDO;
import com.xushicao.accounting.facade.req.OpenAccountReq;
import org.springframework.stereotype.Service;

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
    void addAccount(OpenAccountReq openAccountReq);


    /**
     * 账号生成方法
     *
     * @param openAccountReq 客户请求
     * @return 账号
     */
    String getAccountNo(OpenAccountReq openAccountReq);
}