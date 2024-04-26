/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */


package com.xushicao.accounting.service;

import com.xushicao.accounting.dao.mapper.AccountMapper;
import com.xushicao.accounting.facade.AccountManageFacade;
import com.xushicao.accounting.facade.req.AccountReq;
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
public class AccountManageFacadeNormalTest {

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
     * 开户测试方法
     * 实现开户测试，返回测试结果
     */

    @Test
    void testOpenAccount() {

        AccountReq accountReq = new AccountReq();
        AccountManageResult result = null;

        // case1: 个人人民币账户正常开户
        accountReq.setAccountType("01");
        accountReq.setCurrency("156");
        accountReq.setAccountName("xushichao");

        result = accountManageFacade.openAccount(accountReq);
        Assert.isTrue(result.isSuccess(), "case1:调用服务结果返回失败");
        Assert.notNull(result.getAccountNo(), "case1:返回账户为空");


        // case2: 公司美元账户正常开户
        accountReq.setAccountType("02");
        accountReq.setCurrency("840");
        accountReq.setAccountName("tailinxi");

        result = accountManageFacade.openAccount(accountReq);
        Assert.isTrue(result.isSuccess(), "case2:调用服务结果返回失败");
        Assert.notNull(result.getAccountNo(), "case2:返回账户为空");


        //case3: 内部欧元账户正常开户
        accountReq.setAccountType("03");
        accountReq.setCurrency("978");
        accountReq.setAccountName("lijiangwei");
        accountReq.setRelationCode("1234567891234567891234567912345678912345678912345678912345678912345678910");
        accountReq.setTitleCode("1001");
        accountReq.setReconInst("中国农业银行");
        accountReq.setRelationInstId("PBOC");


        result = accountManageFacade.openAccount(accountReq);
        Assert.isTrue(result.isSuccess(), "case3:调用服务结果返回失败");
        Assert.notNull(result.getAccountNo(), "case3:返回账户为空");


    }

    /**
     * 冻结账户测试方法
     */
    @Test
    void testFreezeAccount() {

        AccountReq accountReq = new AccountReq();
        AccountManageResult result = null;

        //case1: 正常账户冻结
        accountReq.setAccountNo("20000310201978");

        result = accountManageFacade.freezeAccount(accountReq.getAccountNo());
        Assert.isTrue(result.isSuccess(), "case1:调用服务结果返回失败");

        accountManageFacade.unFreezeAccount(accountReq.getAccountNo());

    }

    /**
     * 解冻账户测试方法
     */
    @Test
    void testUnFreezeAccount() {

        AccountReq accountReq = new AccountReq();
        AccountManageResult result = null;

        //case1: 正常账户解冻
        accountReq.setAccountNo("20000310198978");

        result = accountManageFacade.unFreezeAccount(accountReq.getAccountNo());
        Assert.isTrue(result.isSuccess(), "case1:调用服务结果返回失败");
        accountManageFacade.freezeAccount(accountReq.getAccountNo());
    }

    /**
     * 销户测试方法
     */
    @Test
    void testCloseAccount() {

        AccountReq accountReq = new AccountReq();
        AccountManageResult result = null;

        //case1: 正常账户销户
        accountReq.setAccountNo("20000310195978");
        result = accountManageFacade.closeAccount(accountReq.getAccountNo());
        Assert.isTrue(result.isSuccess(), "case1:调用服务结果返回失败");
    }

    @Test
    void testDeposit() {

        String accountNo = null;
        long balance = 100;
        AccountManageResult result = null;

        //case1:个人账户存款
        accountNo = "20000110235156";
        result = accountManageFacade.deposit(accountNo, balance);
        Assert.isTrue(result.isSuccess(), "调用服务结果返回失败");

        //case2;企业账户存款
        accountNo = "20000110232156";
        result = accountManageFacade.deposit(accountNo, balance);
        Assert.isTrue(result.isSuccess(), "调用服务结果返回失败");

    }
}
