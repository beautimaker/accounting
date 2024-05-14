/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.facade.Impl;

import com.xushicao.accounting.dao.entity.AccountDO;
import com.xushicao.accounting.facade.AccountQueryFacade;
import com.xushicao.accounting.facade.result.AccountResult;

import com.xushicao.accounting.facade.result.QueryResult;
import com.xushicao.accounting.service.AccountService;
import com.xushicao.accounting.service.QueryService;
import com.xushicao.accounting.template.QueryCallBack;
import com.xushicao.accounting.template.QueryTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.xushicao.accounting.util.ParaCheckUtil.*;

/**
 * @author Shichao.xu
 * @version $ AccountQueryFacadeImpl, V0.1 2024/4/29 22:08 Shichao.xu Exp $
 */
@RestController
@RequestMapping("/")
public class AccountQueryFacadeImpl implements AccountQueryFacade {

    /**
     * 服务类对象
     */
    @Autowired
    QueryService queryService;

    /**
     * 查询账户方法重写
     *
     * @param accountNo 账号
     * @return 返回结果
     */
    @Override
    @PostMapping("queryAccount")
    public QueryResult QueryAccount(String accountNo) {

        final QueryResult result = new QueryResult();
        QueryTemplate.query(result, new QueryCallBack() {
            @Override
            public void checkParameter() {
                checkParaNotBlank(accountNo, ACCOUNT_NUMBER);
            }

            @Override
            public void doQuery() {
                AccountDO accountDO = queryService.queryAccount(accountNo);
                result.setAccountDO(accountDO);
            }
        });
        return result;
    }
}