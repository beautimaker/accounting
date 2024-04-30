/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.facade.result;


import com.xushicao.accounting.common.ErrorContext;
import com.xushicao.accounting.dao.entity.AccountDO;

/**
 * @author Shichao.xu
 * @version $ AccountManageResult , V0.1 2024/4/8 12:56 Shichao.xu Exp $
 */
public class AccountResult {

    /**
     * 账号生成规则：固定前缀+账号类型+序列号+币种<br/>
     * 示例：2088 01 12345678 0156<br/>
     */
    private String accountNo;

    /**
     * 错误码上下文
     * <li>01-用户请求为空</li>
     * <li>02-内部户别名为空</li>
     * <li>03-用户类型或者币种不在给定范围内</>
     */
    private ErrorContext errorContext;


    /**
     * 账户实体类，包含账户所有信息
     */
    private AccountDO accountDO;

    /**
     * 成功标记<br>
     * 默认为false
     */
    private boolean success = false;

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public ErrorContext getErrorContext() {
        return errorContext;
    }

    public void setErrorContext(ErrorContext errorContext) {
        this.errorContext = errorContext;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public AccountDO getAccountDO() {
        return accountDO;
    }

    public void setAccountDO(AccountDO accountDO) {
        this.accountDO = accountDO;
    }
}
