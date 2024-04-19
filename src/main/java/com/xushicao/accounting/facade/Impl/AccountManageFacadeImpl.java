/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */

package com.xushicao.accounting.facade.Impl;

import com.xushicao.accounting.facade.AccountManageFacade;
import com.xushicao.accounting.facade.req.AccountReq;
import com.xushicao.accounting.facade.result.AccountManageResult;
import com.xushicao.accounting.service.AccountService;
import com.xushicao.accounting.template.TradeCallBack;
import com.xushicao.accounting.template.TradeTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

import static com.xushicao.accounting.util.ParaCheckUtil.*;

/**
 * 开户接口实现类
 * 属性：开户映射接口、序列号生成接口
 * 方法：开户方法、 账户实体生成方法、账户生成方法
 *
 * @author Shichao.xu
 * @version $ AccountManageFacadeImpl, V0.1 2024/4/8 12:56 Shichao.xu Exp $
 */
@RestController
@RequestMapping("/")
public class AccountManageFacadeImpl implements AccountManageFacade {

    /**
     * 账户服务类
     */
    @Autowired
    AccountService accountService;

    /**
     * 开户方法的重写<br/>
     * 用于实现开户
     *
     * @param accountReq 开户请求
     * @return 开户结果
     */
    @Override
    @PostMapping("account")
    public AccountManageResult openAccount(@RequestBody AccountReq accountReq) {


        final AccountManageResult result = new AccountManageResult();

        TradeTemplate.trade(result, new TradeCallBack() {
            @Override
            public void checkParameter() {

                checkParamNotNull(accountReq, USER_REQUEST);

                checkParaNotBlank(accountReq.getAccountType(), ACCOUNT_TYPE);
                checkParaMatch(accountReq.getAccountType(), ACCOUNT_TYPE);

                checkParaNotBlank(accountReq.getCurrency(), ACCOUNT_CURRENCY);
                checkParaMatch(accountReq.getCurrency(), ACCOUNT_CURRENCY);

                if (accountReq.getAccountType().equals("03")) {
                    checkParaNotBlank(accountReq.getAccountName(), ACCOUNT_NAME);
                }

            }

            @Override
            public void doTrade() {
                String accountNo = accountService.openAccount(accountReq);
                result.setAccountNo(accountNo);
            }


        });
        return result;

    }

    /**
     * 冻结账户方法重写
     *
     * @param accountNO 用户账号
     * @return 返回冻结结果
     */
    @Override
    @PostMapping("freezeAccount")
    public AccountManageResult freezeAccount(@RequestBody String accountNO) {

        final AccountManageResult result = new AccountManageResult();//创建结果对象

        TradeTemplate.trade(result, new TradeCallBack() {
            @Override
            public void checkParameter() {
                checkParamNotNull(accountNO, USER_REQUEST);
                checkParaNotBlank(accountNO, ACCOUNT_NUMBER);
            }

            @Override
            public void doTrade() {
                accountService.freezeAccount(accountNO);
            }
        });

        return result;
    }

    /**
     * 解冻账户方法重写
     *
     * @param accountNo 用户账号
     * @return
     */
    @Override
    @PostMapping("unFreezeAccount")
    public AccountManageResult unFreezeAccount(@RequestBody String accountNo) {

        final AccountManageResult result = new AccountManageResult();

        TradeTemplate.trade(result, new TradeCallBack() {
            @Override
            public void checkParameter() {
                checkParaNotBlank(accountNo, ACCOUNT_NUMBER);
            }

            @Override
            public void doTrade() {
                accountService.unFreezeAccount(accountNo);
            }
        });
        return result;
    }

    @Override
    public AccountManageResult closeAccount(@RequestBody String accountNo) {

        final AccountManageResult result = new AccountManageResult();
        TradeTemplate.trade(result, new TradeCallBack() {
            @Override
            public void checkParameter() {
                checkParamNotNull(accountNo, USER_REQUEST);
                checkParaNotBlank(accountNo, ACCOUNT_NUMBER);
            }

            @Override
            public void doTrade() {
                accountService.closeAccount(accountNo);
            }
        });
        return result;
    }
}
