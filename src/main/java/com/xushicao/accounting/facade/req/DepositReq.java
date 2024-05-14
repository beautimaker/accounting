/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.facade.req;

/**
 * 存款请求类
 *
 * @author Shichao.xu
 * @version $ depositReq, V0.1 2024/5/8 22:07 Shichao.xu Exp $
 */
public class DepositReq extends TransReq {

    /**
     * 存款账号
     */
    private String accountNo;

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }
}