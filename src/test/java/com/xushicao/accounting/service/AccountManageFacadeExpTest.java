/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.service;

import com.xushicao.accounting.facade.AccountManageFacade;
import com.xushicao.accounting.facade.req.AccountManageReq;
import com.xushicao.accounting.facade.result.AccountResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;


/**
 * 开户异常场景测试
 *
 * @author Shichao.xu
 * @version $ AccountManageFacadeExpTest, V0.1 2024/4/10 15:32 Shichao.xu Exp $
 */
@SpringBootTest
public class AccountManageFacadeExpTest {

    /**
     * 开户接口对象
     * 实现开户
     */
    @Autowired
    AccountManageFacade accountManageFacade;


    /**
     * 开户异常场景测试方法
     * 测试开户异常
     */
    @Test
    void expTestOpenAccount() {

        AccountManageReq accountManageReq = new AccountManageReq();
        AccountResult result = null;


        // case1: 开户请求对象为空异常
        result = accountManageFacade.openAccount(null);

        Assert.isTrue(!result.isSuccess(), "case1:调用服务结果返回成功");
        Assert.isNull(result.getAccountNo(), "case1:返回账户存在");
        Assert.isTrue(result.getErrorContext().getCodeStr().equals("XU0100002101"), "case1:异常类型不为请求对象为空");


        // case2: 账户类型为内部户时账户名为空异常
        accountManageReq.setAccountType("03");
        accountManageReq.setCurrency("156");

        result = accountManageFacade.openAccount(accountManageReq);
        Assert.isTrue(!result.isSuccess(), "case2:调用服务结果返回成功");
        Assert.isNull(result.getAccountNo(), "case2:返回账户存在");
        Assert.isTrue(result.getErrorContext().getCodeStr().equals("XU0100002101"), "case:异常类型不为账户类型为内部户时账户名为空");


        //case3:账户类型或币种参数不在给定范围内
        accountManageReq.setAccountName("xushichao");
        accountManageReq.setAccountType("06");
        accountManageReq.setCurrency("123");

        result = accountManageFacade.openAccount(accountManageReq);
        Assert.isTrue(!result.isSuccess(), "case3:调用服务结果返回成功");
        Assert.isNull(result.getAccountNo(), "case3:返回账户存在");
        Assert.isTrue(result.getErrorContext().getCodeStr().equals("XU0100002101"), "case3:异常类型不为账户类型或币种参数不在给定范围");


    }

    /**
     * 冻结账户方法异常测试
     */
    @Test
    void expTestFreezeAccount() {
        AccountManageReq accountManageReq = new AccountManageReq();
        AccountResult result = null;

        //case1: 冻结账户账户冻结
        accountManageReq.setAccountNo("20000310192978");

        result = accountManageFacade.freezeAccount(accountManageReq.getAccountNo());
        Assert.isTrue(!result.isSuccess(), "case1:调用服务结果返回成功");
        Assert.isTrue(result.getErrorContext().getCodeStr().equals("XU0100002101"), "异常类型不为账户已冻结");
    }

    /**
     * 解冻方法异常测试
     */
    @Test
    void expTestUnFreezeAccount() {
        AccountManageReq accountManageReq = new AccountManageReq();
        AccountResult result = null;

        //case1: 解冻账户解冻
        accountManageReq.setAccountNo("20000310189978");

        result = accountManageFacade.unFreezeAccount(accountManageReq.getAccountNo());
        Assert.isTrue(!result.isSuccess(), "case1:调用服务结果返回成功");
        Assert.isTrue(result.getErrorContext().getCodeStr().equals("XU0100002101"), "异常类型不为账户已解冻");
    }

    /**
     * 销户方法异常测试
     */
    @Test
    void expTestCloseAccount() {

        AccountManageReq accountManageReq = new AccountManageReq();
        AccountResult result = null;

        //case1: 销户账户销户
        accountManageReq.setAccountNo("20000310170978");

        result = accountManageFacade.closeAccount(accountManageReq.getAccountNo());
        Assert.isTrue(!result.isSuccess(), "case1:调用服务结果返回成功");
        Assert.isTrue(result.getErrorContext().getCodeStr().equals("XU0100002101"), "异常类型不为账户存在余额");
    }
}