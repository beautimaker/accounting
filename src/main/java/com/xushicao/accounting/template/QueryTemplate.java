/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.template;

import com.xushicao.accounting.facade.result.AccountResult;
import com.xushicao.accounting.facade.result.QueryResult;
import com.xushicao.accounting.model.enums.AccountingErrDtlEnum;
import com.xushicao.accounting.model.enums.AccountingErrScenarioEnum;
import com.xushicao.accounting.model.exception.AccountingException;
import org.springframework.dao.DataAccessException;

import javax.management.Query;

/**
 * 查询模板类
 *
 * @author Shichao.xu
 * @version $ QueryTemplate, V0.1 2024/4/29 22:13 Shichao.xu Exp $
 */
public class QueryTemplate extends AbstractTemplate {

    /**
     * 场景码
     */
    public static final AccountingErrScenarioEnum ERR_SCENARIO = AccountingErrScenarioEnum.QUERY;

    public static void query(QueryResult result, QueryCallBack callBack) {
        try {
            callBack.checkParameter();
            callBack.doQuery();
            result.setSuccess(true);

        } catch (AccountingException e) {
            LOGGER.error("查询服务出现异常", e);
            buildFailureResponse(result, ERR_SCENARIO, e);
        } catch (DataAccessException ex) {
            //打印日志
            LOGGER.error("查询服务出现数据库层异常", ex);
            buildFailureResponse(result, ERR_SCENARIO, AccountingErrDtlEnum.DB_EXCEPTION, "查询服务出现数据库层异常");
        } catch (RuntimeException e) {
            //打印日志
            LOGGER.error("查询服务出现未知异常", e);
            buildFailureResponse(result, ERR_SCENARIO, AccountingErrDtlEnum.UNKNOWN_EXCEPTION, "查询服务出现未知异常");
        }
    }

}