/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.service;

import com.xushicao.accounting.dao.entity.AccountDO;

/**
 * 查询服务接口
 *
 * @author Shichao.xu
 * @version $ QueryService, V0.1 2024/5/14 15:16 Shichao.xu Exp $
 */
public interface QueryService {

    /**
     * 账户查询方法
     * 通过传入的账号，获取账户实体类，返回账户信息
     *
     * @param accountNo 账号
     * @return 账户实体类
     */
    AccountDO queryAccount(String accountNo);

}