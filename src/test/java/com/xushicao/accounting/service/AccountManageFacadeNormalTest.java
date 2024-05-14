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
 * 开户测试类
 * 属性：开户接口
 * 方法： 开户测试方法
 *
 * @author Shichao.xu
 * @version $ AccountManageFacadeNormalTest, V0.1 2024/4/8 12:56 Shichao.xu Exp $
 */
@SpringBootTest
public class AccountManageFacadeNormalTest {

    /**
     * 开户接口
     * 通过其调用开户方法
     */
    @Autowired
    private AccountManageFacade accountManageFacade;

    /**
     * 开户测试方法
     * 实现开户测试，返回测试结果
     */

    @Test
    void testOpenAccount() {

        AccountManageReq accountManageReq = new AccountManageReq();
        AccountResult result = null;

        // case1: 个人人民币账户正常开户
        accountManageReq.setAccountType("01");
        accountManageReq.setCurrency("156");
        accountManageReq.setAccountName("xushichao");

        result = accountManageFacade.openAccount(accountManageReq);
        Assert.isTrue(result.isSuccess(), "case1:调用服务结果返回失败");
        Assert.notNull(result.getAccountNo(), "case1:返回账户为空");


        // case2: 公司美元账户正常开户
        accountManageReq.setAccountType("02");
        accountManageReq.setCurrency("840");
        accountManageReq.setAccountName("tailinxi");

        result = accountManageFacade.openAccount(accountManageReq);
        Assert.isTrue(result.isSuccess(), "case2:调用服务结果返回失败");
        Assert.notNull(result.getAccountNo(), "case2:返回账户为空");


        //case3: 内部欧元账户正常开户
        accountManageReq.setAccountType("03");
        accountManageReq.setCurrency("978");
        accountManageReq.setAccountName("lijiangwei");
        accountManageReq.setRelationCode("1234567891234567891234567912345678912345678912345678912345678912345678910");
        accountManageReq.setTitleCode("1001");
        accountManageReq.setReconInst("中国农业银行");
        accountManageReq.setRelationInstId("PBOC");


        result = accountManageFacade.openAccount(accountManageReq);
        Assert.isTrue(result.isSuccess(), "case3:调用服务结果返回失败");
        Assert.notNull(result.getAccountNo(), "case3:返回账户为空");


    }

    /**
     * 冻结账户测试方法
     */
    @Test
    void testFreezeAccount() {

        AccountManageReq accountManageReq = new AccountManageReq();
        AccountResult result = null;

        //case1: 正常账户冻结
        accountManageReq.setAccountNo("20000310201978");

        result = accountManageFacade.freezeAccount(accountManageReq.getAccountNo());
        Assert.isTrue(result.isSuccess(), "case1:调用服务结果返回失败");

        accountManageFacade.unFreezeAccount(accountManageReq.getAccountNo());

    }

    /**
     * 解冻账户测试方法
     */
    @Test
    void testUnFreezeAccount() {

        AccountManageReq accountManageReq = new AccountManageReq();
        AccountResult result = null;

        //case1: 正常账户解冻
        accountManageReq.setAccountNo("20000310198978");

        result = accountManageFacade.unFreezeAccount(accountManageReq.getAccountNo());
        Assert.isTrue(result.isSuccess(), "case1:调用服务结果返回失败");
        accountManageFacade.freezeAccount(accountManageReq.getAccountNo());
    }

    /**
     * 销户测试方法
     */
    @Test
    void testCloseAccount() {

        AccountManageReq accountManageReq = new AccountManageReq();
        AccountResult result = null;

        //case1: 正常账户销户
        accountManageReq.setAccountNo("20000310195978");
        result = accountManageFacade.closeAccount(accountManageReq.getAccountNo());
        Assert.isTrue(result.isSuccess(), "case1:调用服务结果返回失败");
    }


}
