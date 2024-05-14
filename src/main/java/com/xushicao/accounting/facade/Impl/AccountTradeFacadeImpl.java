/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.facade.Impl;

import com.xushicao.accounting.facade.AccountTradeFacade;
import com.xushicao.accounting.facade.req.DepositReq;
import com.xushicao.accounting.facade.req.TransferReq;
import com.xushicao.accounting.facade.req.WithdrawReq;
import com.xushicao.accounting.facade.result.AccountResult;
import com.xushicao.accounting.service.AccountService;
import com.xushicao.accounting.service.DepositService;
import com.xushicao.accounting.service.TransferService;
import com.xushicao.accounting.service.WithdrawService;
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

    /**
     * 取款服务对象
     */
    @Autowired
    DepositService depositService;

    /**
     * 取款服务对象
     */
    @Autowired
    TransferService transferService;

    /**
     * 转账服务对象
     */
    @Autowired
    WithdrawService withdrawService;

    /**
     * 取款方法重写
     *
     * @param withdrawReq q
     * @return
     */
    @Override
    @PostMapping("withdraw")
    public AccountResult withdraw(WithdrawReq withdrawReq) {

        final AccountResult result = new AccountResult();
        TradeTemplate.trade(result, new TradeCallBack() {
            @Override
            public void checkParameter() {
                checkParamNotNull(withdrawReq, USER_REQUEST);
                checkParaNotBlank(withdrawReq.getAccountNo(), ACCOUNT_NUMBER);
                checkTransParams(withdrawReq);
            }

            @Override
            public void doTrade() {
                withdrawService.withdraw(withdrawReq);
            }
        });
        return result;
    }

    /**
     * 转账方法重写
     *
     * @param transferReq 转账请求
     * @return 返回结果
     */
    @Override
    @PostMapping("transfer")
    public AccountResult transfer(TransferReq transferReq) {

        final AccountResult result = new AccountResult();

        TradeTemplate.trade(result, new TradeCallBack() {
            @Override
            public void checkParameter() {
                checkParamNotNull(transferReq, USER_REQUEST);
                checkParaNotBlank(transferReq.getAccountFromNo(), ACCOUNT_NUMBER);
                checkParaNotBlank(transferReq.getAccountToNo(), ACCOUNT_NUMBER);
                checkTransParams(transferReq);
            }

            @Override
            public void doTrade() {
                transferService.transfer(transferReq);
            }
        });

        return result;
    }

    /**
     * 存款方法重写
     *
     * @param depositReq 存款请求
     * @return 返回结果
     */
    @Override
    @PostMapping("deposit")
    public AccountResult deposit(DepositReq depositReq) {
        final AccountResult result = new AccountResult();
        TradeTemplate.trade(result, new TradeCallBack() {
            @Override
            public void checkParameter() {
                checkParamNotNull(depositReq, USER_REQUEST);
                checkParaNotBlank(depositReq.getAccountNo(), ACCOUNT_NUMBER);
                checkTransParams(depositReq);
            }

            @Override
            public void doTrade() {
                depositService.deposit(depositReq);
            }
        });
        return result;
    }

}