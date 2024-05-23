/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.service;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.xushicao.accounting.facade.AccountTradeFacade;
import com.xushicao.accounting.facade.req.DepositReq;
import com.xushicao.accounting.facade.req.TransferReq;
import com.xushicao.accounting.facade.req.WithdrawReq;
import com.xushicao.accounting.facade.result.AccountResult;
import com.xushicao.accounting.facade.result.BaseResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
        BaseResult result;
        String accountNo = "20000110239156";
        WithdrawReq depositReq = new WithdrawReq();
        depositReq.setOperatorID("760479708");
        depositReq.setOrderNo("003");
        depositReq.setReconInst("苏州银行");
        depositReq.setOutDate(LocalDateTime.now());
        depositReq.setTransDT(LocalDateTime.now());
        depositReq.setTransCode("DEP");
        depositReq.setSubTransCode("01");
        depositReq.setCurrency("156");
        depositReq.setAmount(100);
        depositReq.setAccountNo(accountNo);

        result = accountTradeFacade.withdraw(depositReq);
        Assert.isTrue(result.isSuccess(), "调用返回结果失败");

    }

    @Test
    void testTransfer() {
        BaseResult result;
        TransferReq depositReq = new TransferReq();
        String accountFromNo = "20000110238156";
        String accountToNo = "20000110239156";
        depositReq.setOperatorID("760479708");
        depositReq.setOrderNo("004");
        depositReq.setReconInst("苏州银行");
        depositReq.setOutDate(LocalDateTime.now());
        depositReq.setTransDT(LocalDateTime.now());
        depositReq.setTransCode("DEP");
        depositReq.setSubTransCode("01");
        depositReq.setCurrency("156");
        depositReq.setAmount(100);
        depositReq.setAccountFromNo(accountFromNo);
        depositReq.setAccountToNo(accountToNo);
        result = accountTradeFacade.transfer(depositReq);
        Assert.isTrue(result.isSuccess(), "返回调用结果失败");
    }

    @Test
    void testDeposit() {

        DepositReq depositReq = new DepositReq();
        String accountNo = null;
        depositReq.setAmount(100);
        BaseResult result = null;
        depositReq.setOperatorID("760479708");
        depositReq.setOrderNo("001");
        depositReq.setReconInst("苏州银行");
        depositReq.setTransDT(LocalDateTime.now());
        depositReq.setTransCode("DEP");
        depositReq.setSubTransCode("01");
        depositReq.setCurrency("156");
        depositReq.setOutDate(LocalDateTime.now());
        System.out.println(depositReq.getOutDate());
        //case1:个人账户存款
        depositReq.setAccountNo("20000110235156");
        result = accountTradeFacade.deposit(depositReq);
        Assert.isTrue(result.isSuccess(), "调用服务结果返回失败");
        Assert.notNull(result.getTransInfo().getID(), "返回订单id失败");
        //case2;企业账户存款
        depositReq.setAccountNo("20000210232156");
        depositReq.setOrderNo("002");
        result = accountTradeFacade.deposit(depositReq);
        Assert.isTrue(result.isSuccess(), "调用服务结果返回失败");
        Assert.notNull(result.getTransInfo().getID(), "返回订单id失败");

    }

}