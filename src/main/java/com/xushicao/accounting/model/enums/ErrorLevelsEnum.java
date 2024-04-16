package com.xushicao.accounting.model.enums;

/**
 * 错误级别
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
public enum ErrorLevelsEnum {

    WARN("1", "WARN级别"),

    ERROR("2", "ERROR级别"),

    FATAL("3", "FATAL级别");

    // ~~~ 属性定义 ~~~

    /**
     * 枚举编码
     */
    private final String code;

    /**
     * 描述说明
     */
    private final String description;

    /**
     * 私有构造函数。
     *
     * @param code        枚举编码
     * @param description 描述说明
     */
    private ErrorLevelsEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * 通过枚举<code>code</code>获得枚举
     *
     * @param code 枚举编码
     * @return 错误明细枚举
     */
    public static ErrorLevelsEnum getByCode(String code) {
        for (ErrorLevelsEnum detailCode : values()) {
            if (detailCode.getCode().equals(code)) {

                return detailCode;
            }
        }
        return null;
    }

    // ~~~容器方法 ~~~

    /**
     * @return Returns the code.
     */
    public String getCode() {
        return code;
    }

    /**
     * @return Returns the description.
     */
    public String getDescription() {
        return description;
    }
}
