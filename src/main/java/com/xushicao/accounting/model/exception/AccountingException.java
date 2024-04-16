/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.model.exception;

import com.xushicao.accounting.model.enums.AccountingErrDtlEnum;

/**
 * 异常基类
 *
 * @author Shichao.xu
 * @version $ AccountingException, V0.1 2024/4/14 16:47 Shichao.xu Exp $
 */

public class AccountingException extends RuntimeException {

    /**
     * 结果枚举
     */
    private final AccountingErrDtlEnum resultCodEnum;

    /**
     * 异常信息
     */
    private String msg;

    /**
     * 构造函数
     *
     * @param resultCodEnum 错误码
     */
    public AccountingException(AccountingErrDtlEnum resultCodEnum) {
        this.resultCodEnum = resultCodEnum;
    }

    /**
     * 包含额外信息的构造函数
     *
     * @param resulCodEnum 错误码
     * @param msg          额外的信息，用于打印到日志中方便查找问题
     */
    public AccountingException(AccountingErrDtlEnum resulCodEnum, String msg) {
        this.resultCodEnum = resulCodEnum;
        this.msg = msg;
    }

    /**
     * 构造函数，包含额外信息
     *
     * @param resulCodEnum
     * @param cause
     */
    public AccountingException(AccountingErrDtlEnum resulCodEnum, Throwable cause) {
        super(cause);
        this.resultCodEnum = resulCodEnum;
    }

    //重写了一个getMessage方法

    //~~~ 属性方法 ~~~

    /**
     * Getter method for property <tt>resultCodeEnum</tt>.
     *
     * @return property value of resultCodeEnum
     */
    public AccountingErrDtlEnum getResulCodEnum() {
        return resultCodEnum;
    }

    /**
     * Getter method for property <tt>msg</tt>.
     *
     * @return property value of msg
     */
    public String getMsg() {
        return msg;
    }
}