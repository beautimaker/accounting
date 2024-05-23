/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.model;


import java.time.LocalDateTime;

/**
 * 账户业务类
 *
 * @author Shichao.xu
 * @version $ Account, V0.1 2024/5/11 16:49 Shichao.xu Exp $
 */
public abstract class Account {

    /**
     * 账号
     */
    protected String accountNo;

    /**
     * 余额
     */
    protected long balance;

    /**
     * 交易时间
     */
    protected LocalDateTime transDT;

    /**
     * 最后记账时间
     */
    protected LocalDateTime lastTransTime;

    public LocalDateTime getLastTransTime() {
        return lastTransTime;
    }

    public void setLastTransTime(LocalDateTime lastTransTime) {
        this.lastTransTime = lastTransTime;
    }

    public LocalDateTime getTransDT() {
        return transDT;
    }

    public void setTransDT(LocalDateTime transDT) {
        this.transDT = transDT;
    }

    public abstract void credit(long amount);

    public abstract void debit(long amount);

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }
}