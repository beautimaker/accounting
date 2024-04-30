/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.service;

import com.xushicao.accounting.facade.AccountTradeFacade;
import com.xushicao.accounting.facade.result.AccountResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

/**
 * 账号操作门面测试
 *
 * @author Shichao.xu
 * @version $ AccountTradeFacadeNormalTest, V0.1 2024/4/29 23:15 Shichao.xu Exp $
 */
@SpringBootTest
public class AccountTradeFacadeNormalTest {

    @Autowired
    AccountTradeFacade accountTradeFacade;

    @Test
    void testWithdraw() {
        AccountResult result;
        String accountNo = "20000210239840";
        long amount = 100;

        result = accountTradeFacade.withdraw(accountNo, amount);
        Assert.isTrue(result.isSuccess(), "调用返回结果失败");

    }

    @Test
    void testTransfer() {
        AccountResult result;
        String accountFromNo = "20000110238156";
        String accountToNo = "20000210239840";
        long amount = 1;

        result = accountTradeFacade.transfer(accountFromNo, accountToNo, amount);
        Assert.isTrue(result.isSuccess(), "返回调用结果失败");
    }

}