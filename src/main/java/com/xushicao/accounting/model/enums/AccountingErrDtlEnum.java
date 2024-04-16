package com.xushicao.accounting.model.enums;

/**
 * 错误码明显段定义枚举
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
public enum AccountingErrDtlEnum {

    //公共类错误码
    /**
     * 其它未知异常
     */
    UNKNOWN_EXCEPTION("999", ErrorLevelsEnum.ERROR, "其它未知异常"),

    /**
     * 配置错误
     */
    CONFIGURATION_ERROR("001", ErrorLevelsEnum.FATAL, "配置错误"),

    /**
     * 数据库异常
     */
    DB_EXCEPTION("002", ErrorLevelsEnum.ERROR, "数据库异常"),

    /**
     * 数据更新异常
     */
    DATA_UPDATE_EXCEPTION("003", ErrorLevelsEnum.ERROR, "数据库异常"),

    //业务处理类
    REQ_PARAM_NOT_VALID("101", ErrorLevelsEnum.WARN, "服务调用请求信息不合法"),

    ;
    // ~~~ 属性定义 ~~~

    /**
     * 枚举编码
     */
    private final String code;

    /**
     * 错误级别
     */
    private final ErrorLevelsEnum errorLevel;

    /**
     * 描述说明
     */
    private final String description;

    /**
     * 私有构造函数。
     *
     * @param code        枚举编码
     * @param errorLevel  错误级别
     * @param description 描述说明
     */
    private AccountingErrDtlEnum(String code, ErrorLevelsEnum errorLevel, String description) {
        this.code = code;
        this.errorLevel = errorLevel;
        this.description = description;
    }

    /**
     * 通过枚举<code>code</code>获得枚举
     *
     * @param code 枚举编码
     * @return 支付错误明细枚举
     */
    public static AccountingErrDtlEnum getByCode(String code) {
        for (AccountingErrDtlEnum detailCode : values()) {
            if (detailCode.getCode().equals(code)) {

                return detailCode;
            }
        }
        return null;
    }


    // ~~~容器方法 ~~~

    /**
     * @return Returns the description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter method for property <tt>errorLevvel</tt>.
     *
     * @return property value of errorLevvel
     */
    public ErrorLevelsEnum getErrorLevel() {
        return errorLevel;
    }

    /**
     * @return Returns the code.
     */
    public String getCode() {
        return code;
    }

}
