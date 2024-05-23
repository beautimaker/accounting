/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.model;


import com.xushicao.accounting.dao.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author Shichao.xu
 * @version $ CreditAccount, V0.1 2024/5/11 16:51 Shichao.xu Exp $
 */

public class CreditAccount extends Account {


    /**
     * 有参构造器
     *
     * @param accountNo 账号
     * @param balance   余额
     */
    public CreditAccount(String accountNo, long balance, LocalDateTime transDT, LocalDateTime lastTransTime) {
        this.accountNo = accountNo;
        this.balance = balance;
        this.transDT = transDT;
        this.lastTransTime = lastTransTime;
    }

    /**
     * credit方法重写
     */
    @Override
    public void credit(long amount) {
        balance = balance + amount;
    }

    /**
     * debit方法重写
     */
    @Override
    public void debit(long amount) {
        balance = balance - amount;
    }
}
