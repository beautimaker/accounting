/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.util;

import com.xushicao.accounting.common.ErrorContext;
import com.xushicao.accounting.model.enums.AccountingErrDtlEnum;
import com.xushicao.accounting.model.enums.AccountingErrScenarioEnum;
import com.xushicao.accounting.model.exception.AccountingException;

/**
 * @author Shichao.xu
 * @version $ ErrorContextUtil, V0.1 2024/4/14 17:05 Shichao.xu Exp $
 */

public final class ErrorContextUtil {
    /**
     * 错误码前缀
     */
    private final static String ERR_CODE_PREFIX = "XU";

    /**
     * 错误码版本
     */
    private final static String ERR_CODE_VERSION = "0";

    /**
     * 错误码类型
     */
    private final static String ERR_CODE_TYPE = "0";

    /**
     * 生成标准错误码
     *
     * @param scenario  场景
     * @param exception 异常
     * @return 错误码上下文
     */
    public static ErrorContext genErrorContext(AccountingErrScenarioEnum scenario,
                                               AccountingException exception) {
        AccountingErrDtlEnum detail = exception.getResulCodEnum();

        String desc = scenario.getDescription();//场景说明

        desc = desc + "/" + detail.getDescription();//错误码说明

        desc = desc + "/" + exception.getMessage();//错误信息

        return genErrorContext(scenario, detail, desc);
    }

    /**
     * 生成标准错误码
     *
     * @param scenario 场景
     * @param detail   错误明细
     * @param message  错误信息
     * @return 错误上下文
     */
    public static ErrorContext genErrorContext(AccountingErrScenarioEnum scenario,
                                               AccountingErrDtlEnum detail, String message) {
        //错误码前缀
        String codeStr = ERR_CODE_PREFIX;
        //错误码版本
        codeStr = codeStr + ERR_CODE_TYPE;
        //错误码级别
        codeStr = codeStr + detail.getErrorLevel().getCode();
        //错误码类型
        codeStr = codeStr + ERR_CODE_TYPE;
        //场景编码
        codeStr = codeStr + scenario.getCode();
        //错误码
        codeStr = codeStr + detail.getCode();

        ErrorContext errorContext = new ErrorContext();
        errorContext.setCodeStr(codeStr);
        errorContext.setDesc(message);

        return errorContext;

    }


}