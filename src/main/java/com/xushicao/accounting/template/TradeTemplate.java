/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.template;

import com.xushicao.accounting.facade.result.AccountManageResult;
import com.xushicao.accounting.model.enums.AccountingErrScenarioEnum;
import com.xushicao.accounting.model.exception.AccountingException;
import com.xushicao.accounting.util.LoggerUtil;

/**
 * @author Shichao.xu
 * @version $ QueryTemplate, V0.1 2024/4/14 16:01 Shichao.xu Exp $
 */

public class TradeTemplate extends AbstractTemplate {

    /**
     * 场景码
     */
    public static final AccountingErrScenarioEnum ERR_SCENARIO = AccountingErrScenarioEnum.QUERY;

    /**
     * 查询模板
     *
     * @param result   查询结果
     * @param callBcak 回调接口
     */
    public static void trade(AccountManageResult result, TradeCallBack callBcak) {
        try {
            callBcak.checkParameter();
            callBcak.doTrade();
            buildSuccessResponse(result);

        } catch (AccountingException e) {
            LoggerUtil.warn(LOGGER, "查询服务异常", e);
            buildFailureResponse(result, ERR_SCENARIO, e);
        } finally {

        }

    }
}