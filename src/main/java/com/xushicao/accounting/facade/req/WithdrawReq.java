/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.facade.req;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 取款请求类
 *
 * @author Shichao.xu
 * @version $ withdrawReq, V0.1 2024/5/10 21:22 Shichao.xu Exp $
 */
public class WithdrawReq extends TransReq {

    /**
     * 取款账号
     */
    private String accountNo;

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }
}