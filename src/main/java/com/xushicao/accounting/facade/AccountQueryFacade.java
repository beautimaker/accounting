/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.facade;

import com.xushicao.accounting.dao.entity.AccountDO;
import com.xushicao.accounting.facade.result.AccountResult;

/**
 * 账户查询门面接口
 *
 * @author Shichao.xu
 * @version $ AccountQueryFacade, V0.1 2024/4/29 22:01 Shichao.xu Exp $
 */
public interface AccountQueryFacade {

    /**
     * 账户查询方法
     *
     * @param accountNo 账号
     * @return 账户实体类
     */
    AccountResult QueryAccount(String accountNo);

}