/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.facade.req;

/**
 * 查询请求类
 *
 * @author Shichao.xu
 * @version $ QueryReq, V0.1 2024/5/10 21:41 Shichao.xu Exp $
 */
public class QueryReq {

    /**
     * 用户账号
     */
    private String accountNo;

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }
}