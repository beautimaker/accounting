/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.dao.entity;

import java.time.LocalDateTime;

/**
 * 余额信息监控表实体类
 *
 * @author Shichao.xu
 * @version $ AccountDailyDO, V0.1 2024/5/22 14:00 Shichao.xu Exp $
 */
public class AccountDailyDO {

    /**
     * 账号
     */
    private String accountNo;

    /**
     * 交易日期
     */
    private LocalDateTime date;

    /**
     * 期初余额
     */
    private long openingBalance;

    /**
     * 期末余额
     */
    private long closingBalance;

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public long getOpeningBalance() {
        return openingBalance;
    }

    public void setOpeningBalance(long openingBalance) {
        this.openingBalance = openingBalance;
    }

    public long getClosingBalance() {
        return closingBalance;
    }

    public void setClosingBalance(long closingBalance) {
        this.closingBalance = closingBalance;
    }
}