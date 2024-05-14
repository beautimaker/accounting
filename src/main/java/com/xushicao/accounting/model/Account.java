/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.model;

import com.xushicao.accounting.service.Impl.BaseService;

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
    String accountNo;

    /**
     * 余额
     */
    long balance;

    /**
     * 上期余额
     */
    long prevBalance;

    public long getPrevBalance() {
        return prevBalance;
    }

    public void setPrevBalance(long prevBalance) {
        this.prevBalance = prevBalance;
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