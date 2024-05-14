/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.facade.result;


import com.xushicao.accounting.common.ErrorContext;
import com.xushicao.accounting.dao.entity.AccountDO;

/**
 * 开户结果类
 *
 * @author Shichao.xu
 * @version $ AccountManageResult , V0.1 2024/4/8 12:56 Shichao.xu Exp $
 */
public class AccountResult extends BaseResult {

    /**
     * 账号生成规则：固定前缀+账号类型+序列号+币种<br/>
     * 示例：2088 01 12345678 0156<br/>
     */
    private String accountNo;

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }
}
