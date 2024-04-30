/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.service;

import com.xushicao.accounting.facade.AccountQueryFacade;
import com.xushicao.accounting.facade.result.AccountResult;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

/**
 * 查询门面测试类
 *
 * @author Shichao.xu
 * @version $ AccountQueryFacadeNormalTest, V0.1 2024/4/29 23:02 Shichao.xu Exp $
 */
@SpringBootTest
public class AccountQueryFacadeNormalTest {

    @Autowired
    AccountQueryFacade accountQueryFacade;

    @Test
    void testQueryAccount() {

        AccountResult result;

        //case1 正常账户查询
        String accountNo = "20000310240978";
        result = accountQueryFacade.QueryAccount(accountNo);
        Assert.isTrue(result.isSuccess(), "调用服务结果返回失败");
        Assert.notNull(result.getAccountDO(), "返回账户信息为空");
        System.out.println(result.getAccountDO());
    }


}