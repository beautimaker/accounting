/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.facade.Impl;

import com.xushicao.accounting.facade.AccountTradeFacade;
import com.xushicao.accounting.facade.result.AccountResult;
import com.xushicao.accounting.service.AccountService;
import com.xushicao.accounting.template.QueryTemplate;
import com.xushicao.accounting.template.TradeCallBack;
import com.xushicao.accounting.template.TradeTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.xushicao.accounting.util.ParaCheckUtil.*;

/**
 * 账户交易门面实现类
 *
 * @author Shichao.xu
 * @version $ AccountTradeFacade, V0.1 2024/4/29 19:46 Shichao.xu Exp $
 */
@RestController
@RequestMapping("/")
public class AccountTradeFacadeImpl implements AccountTradeFacade {

    /**
     * 账户服务类对象
     */
    @Autowired
    AccountService accountService;

    @Override
    @PostMapping("withdraw")
    public AccountResult withdraw(String accountNo, long amount) {

        final AccountResult result = new AccountResult();
        TradeTemplate.trade(result, new TradeCallBack() {
            @Override
            public void checkParameter() {
                checkParaNotBlank(accountNo, ACCOUNT_NUMBER);
                checkParamNotNull(amount, ACCOUNT_AMOUNT);

            }

            @Override
            public void doTrade() {
                accountService.withdraw(accountNo, amount);
            }
        });
        return result;
    }

    @Override
    @PostMapping("transfer")
    public AccountResult transfer(String accountFromNo, String accountToNo, long amount) {

        final AccountResult result = new AccountResult();

        TradeTemplate.trade(result, new TradeCallBack() {
            @Override
            public void checkParameter() {
                checkParaNotBlank(accountFromNo, ACCOUNT_NUMBER);
                checkParaNotBlank(accountToNo, ACCOUNT_NUMBER);
                checkParamNotNull(amount, ACCOUNT_AMOUNT);
            }

            @Override
            public void doTrade() {
                accountService.transfer(accountFromNo, accountToNo, amount);
            }
        });

        return result;
    }


}