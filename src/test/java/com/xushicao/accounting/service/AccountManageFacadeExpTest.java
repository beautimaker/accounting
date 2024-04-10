/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.service;

import com.xushicao.accounting.dao.mapper.AccountMapper;
import com.xushicao.accounting.facade.AccountManageFacade;
import com.xushicao.accounting.facade.req.OpenAccountReq;
import com.xushicao.accounting.facade.result.AccountManageResult;
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

        OpenAccountReq openAccountReq = new OpenAccountReq();
        AccountManageResult result = null;


        // case1: 开户请求对象为空异常
        result = accountManageFacade.openAccount(null);

        Assert.isTrue(!result.isSuccess(), "case1:调用服务结果返回成功");
        Assert.isNull(result.getAccountNo(), "case1:返回账户存在");
        Assert.isTrue(result.getErrorCode().equals("01"), "异常类型不为请求对象为空");


        // case2: 账户类型为内部户时账户名为空异常
        openAccountReq.setAccountType("03");
        openAccountReq.setCurrency("156");

        result = accountManageFacade.openAccount(openAccountReq);
        Assert.isTrue(!result.isSuccess(), "case2:调用服务结果返回成功");
        Assert.isNull(result.getAccountNo(), "case2:返回账户存在");
        Assert.isTrue(result.getErrorCode().equals("02"), "异常类型不为账户类型为内部户时账户名为空");


        //case3:账户类型或币种参数不在给定范围内
        openAccountReq.setAccountName("xushichao");
        openAccountReq.setAccountType("06");
        openAccountReq.setCurrency("123");

        result = accountManageFacade.openAccount(openAccountReq);
        Assert.isTrue(!result.isSuccess(), "case3:调用服务结果返回成功");
        Assert.isNull(result.getAccountNo(), "case3:返回账户存在");
        Assert.isTrue(result.getErrorCode().equals("03"), "异常类型不为账户类型或币种参数不在给定范围");


    }

}