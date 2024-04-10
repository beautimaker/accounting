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
 * 开户测试类
 * 属性：开户接口
 * 方法： 开户测试方法
 *
 * @author Shichao.xu
 * @version $ AccountManageFacadeNormalTest, V0.1 2024/4/8 12:56 Shichao.xu Exp $
 */
@SpringBootTest
public class AccountDOManageFacadeNormalTest {

    /**
     * 开户映射接口
     * 实现与数据库相关操作
     */
    @Autowired
    AccountMapper accountMapper;

    /**
     * 开户接口
     * 通过其调用开户方法
     */
    @Autowired
    private AccountManageFacade accountManageFacade;

    /**
     * 开户测试防方法
     * 实现开户测试，返回测试结果
     */

    @Test
    void testOpenAccount() {

        OpenAccountReq openAccountReq = new OpenAccountReq();
        AccountManageResult result = null;

        // case1: 个人人民币账户正常开户
        openAccountReq.setAccountType("01");
        openAccountReq.setCurrency("156");
        openAccountReq.setAccountName("xushichao");

        result = accountManageFacade.openAccount(openAccountReq);
        Assert.isTrue(result.isSuccess(), "case1:调用服务结果返回失败");
        Assert.notNull(result.getAccountNo(), "case1:返回账户为空");


        // case2: 公司美元账户正常开户
        openAccountReq.setAccountType("02");
        openAccountReq.setCurrency("840");
        openAccountReq.setAccountName("tailinxi");

        result = accountManageFacade.openAccount(openAccountReq);
        Assert.isTrue(result.isSuccess(), "case2:调用服务结果返回失败");
        Assert.notNull(result.getAccountNo(), "case2:返回账户为空");


        //case3: 内部欧元账户正常开户
        openAccountReq.setAccountType("03");
        openAccountReq.setCurrency("978");
        openAccountReq.setAccountName("lijiangwei");

        result = accountManageFacade.openAccount(openAccountReq);
        Assert.isTrue(result.isSuccess(), "case3:调用服务结果返回失败");
        Assert.notNull(result.getAccountNo(), "case3:返回账户为空");


    }
}
