/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.model;

import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

/**
 * @author Shichao.xu
 * @version $ Factory, V0.1 2024/5/11 18:09 Shichao.xu Exp $
 */
public class Factory {

    public Account create(String direction, String accountNo, long balance, LocalDateTime transDT, LocalDateTime
            lastTransTime) {
        if (direction.equals("D")) {
            return new DebitAccount(accountNo, balance, transDT, lastTransTime);
        } else {
            return new CreditAccount(accountNo, balance, transDT, lastTransTime);
        }
    }


}