/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.model;

import com.xushicao.accounting.dao.mapper.AccountMapper;
import com.xushicao.accounting.facade.req.DepositReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author Shichao.xu
 * @version $ DebitAccount, V0.1 2024/5/11 16:54 Shichao.xu Exp $
 */
public class DebitAccount extends Account {


    public DebitAccount(String accountNo, long balance, LocalDateTime transDT, LocalDateTime lastTransTime) {
        this.accountNo = accountNo;
        this.balance = balance;
        this.transDT = transDT;
        this.lastTransTime = lastTransTime;
    }


    /**
     * 贷方方法重写
     *
     * @param amount
     */
    @Override
    public void credit(long amount) {
        balance = balance - amount;

    }

    /**
     * 借方方法重写
     *
     * @param amount
     */
    @Override
    public void debit(long amount) {
        balance = balance + amount;
    }

}


