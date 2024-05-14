/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.facade.req;

/**
 * 转账请求类
 *
 * @author Shichao.xu
 * @version $ TrransferReq, V0.1 2024/5/10 21:23 Shichao.xu Exp $
 */
public class TransferReq extends TransReq {

    /**
     * 转出账号
     */
    private String accountFromNo;

    /**
     * 转入账号
     */
    private String accountToNo;

    public String getAccountFromNo() {
        return accountFromNo;
    }

    public void setAccountFromNo(String accountFromNo) {
        this.accountFromNo = accountFromNo;
    }

    public String getAccountToNo() {
        return accountToNo;
    }

    public void setAccountToNo(String accountToNo) {
        this.accountToNo = accountToNo;
    }
}