/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.template;

import com.xushicao.accounting.common.ErrorContext;
import com.xushicao.accounting.facade.result.AccountResult;
import com.xushicao.accounting.facade.result.BaseResult;
import com.xushicao.accounting.model.enums.AccountingErrDtlEnum;
import com.xushicao.accounting.model.enums.AccountingErrScenarioEnum;
import com.xushicao.accounting.model.exception.AccountingException;
import com.xushicao.accounting.util.ErrorContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 处理模板基类
 *
 * @author Shichao.xu
 * @version $ AbstractTemplate, V0.1 2024/4/14 16:02 Shichao.xu Exp $
 */

public class AbstractTemplate {

    /**
     * logger
     */
    protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractTemplate.class);

    /**
     * 构建处理成功的返回结果
     *
     * @param result 处理结果
     */
    protected static void buildSuccessResponse(BaseResult result) {
        result.setSuccess(true);
    }

    /**
     * 构建处理失败的返回结果
     *
     * @param result         处理结果
     * @param scenarioEnum   错误场景
     * @param transException 业务异常
     */
    protected static void buildFailureResponse(BaseResult result,
                                               AccountingErrScenarioEnum scenarioEnum,
                                               AccountingException transException) {
        result.setSuccess(false);
        ErrorContext errorContext = ErrorContextUtil.genErrorContext(scenarioEnum, transException);
        result.setErrorContext(errorContext);
    }

    /**
     * 构造失败的结果对象
     *
     * @param result       处理结果
     * @param scenarioEnum 错误场景
     * @param detailEnum   错误明细枚举
     * @param errorMsg     错误信息
     */
    protected static void buildFailureResponse(BaseResult result,
                                               AccountingErrScenarioEnum scenarioEnum,
                                               AccountingErrDtlEnum detailEnum, String errorMsg) {
        result.setSuccess(false);
        ErrorContext errorContext = ErrorContextUtil.genErrorContext(scenarioEnum, detailEnum, errorMsg);
        result.setErrorContext(errorContext);

    }
}