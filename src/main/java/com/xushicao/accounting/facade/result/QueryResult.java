/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.facade.result;

import com.xushicao.accounting.common.ErrorContext;
import com.xushicao.accounting.dao.entity.AccountDO;

/**
 * 查询结果类
 *
 * @author Shichao.xu
 * @version $ QueryResult, V0.1 2024/5/14 14:47 Shichao.xu Exp $
 */
public class QueryResult extends BaseResult {

    /**
     * 账户实体类，包含账户所有信息
     */
    private AccountDO accountDO;


    public AccountDO getAccountDO() {
        return accountDO;
    }

    public void setAccountDO(AccountDO accountDO) {
        this.accountDO = accountDO;
    }
}