/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.template;

import com.xushicao.accounting.facade.result.AccountResult;
import com.xushicao.accounting.facade.result.BaseResult;
import com.xushicao.accounting.model.enums.AccountingErrDtlEnum;
import com.xushicao.accounting.model.enums.AccountingErrScenarioEnum;
import com.xushicao.accounting.model.exception.AccountingException;
import org.springframework.dao.DataAccessException;


/**
 * 交易模板类
 * 执行业务逻辑，捕获异常，构建返回结果
 *
 * @author Shichao.xu
 * @version $ QueryTemplate, V0.1 2024/4/14 16:01 Shichao.xu Exp $
 */

public class TradeTemplate extends AbstractTemplate {

    /**
     * 场景码
     */
    public static final AccountingErrScenarioEnum ERR_SCENARIO = AccountingErrScenarioEnum.TRADE;

    /**
     * 操作模板
     *
     * @param result   返回结果
     * @param callBcak 回调接口
     */
    public static void trade(BaseResult result, TradeCallBack callBcak) {
        try {
            callBcak.checkParameter();
            callBcak.doTrade();
            result.setSuccess(true);
        } catch (AccountingException e) {
            LOGGER.error("交易服务出现异常", e);
            buildFailureResponse(result, ERR_SCENARIO, e);
        } catch (DataAccessException ex) {
            //打印日志
            LOGGER.error("交易服务出现数据库层异常", ex);
            buildFailureResponse(result, ERR_SCENARIO, AccountingErrDtlEnum.DB_EXCEPTION, "交易服务出现数据库层异常");
        } catch (RuntimeException e) {
            //打印日志
            LOGGER.error("交易服务出现未知异常", e);
            buildFailureResponse(result, ERR_SCENARIO, AccountingErrDtlEnum.UNKNOWN_EXCEPTION, "交易服务出现未知异常");
        } finally {
            //创建线程保留日志对象

        }

    }
}