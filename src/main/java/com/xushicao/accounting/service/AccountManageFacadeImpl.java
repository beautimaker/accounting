/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */

package com.xushicao.accounting.service;

import com.xushicao.accounting.common.AccountingConstants;
import com.xushicao.accounting.facade.AccountManageFacade;
import com.xushicao.accounting.facade.req.OpenAccountReq;
import com.xushicao.accounting.facade.result.AccountManageResult;
import com.xushicao.accounting.log.DigestLogAnnotated;
import com.xushicao.accounting.template.TradeCallBack;
import com.xushicao.accounting.template.TradeTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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
     * @param openAccountReq 开户请求
     * @return 开户结果
     */
    @Override
    @PostMapping("account")
    @DigestLogAnnotated(AccountingConstants.QUERY_DIGEST_LOG)
    public AccountManageResult openAccount(@RequestBody OpenAccountReq openAccountReq) {


        final AccountManageResult result = new AccountManageResult();

        TradeTemplate.trade(result, new TradeCallBack() {
            @Override
            public void checkParameter() {

                checkParamNotNull(openAccountReq, USER_REQUEST);

                checkParaNotBlank(openAccountReq.getAccountType(), ACCOUNT_TYPE);
                checkParaMatch(openAccountReq.getAccountType(), ACCOUNT_TYPE);

                checkParaNotBlank(openAccountReq.getCurrency(), ACCOUNT_CURRENCY);
                checkParaMatch(openAccountReq.getCurrency(), ACCOUNT_CURRENCY);

                if (openAccountReq.getAccountType().equals("03")) {
                    checkParaNotBlank(openAccountReq.getAccountName(), ACCOUNT_NAME);
                }

            }

            @Override
            @Transactional
            public void doTrade() {
                accountService.addAccount(openAccountReq);
                result.setAccountNo(accountService.getAccountNo(openAccountReq));
            }

//            @Override
//            public QueryDigestLog buildDigestLog() {
//                return new QueryDigestLog(openAccountReq);
//            }
        });

        return result;
    }

}
