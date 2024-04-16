package com.xushicao.accounting.model.enums;

/**
 * 错误码场景段
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
public enum AccountingErrScenarioEnum {

    MNG("0001", "Accounting管理类场景"),

    TRADE("0002", "Accounting交易类场景"),

    QUERY("0003", "Accounting查询类场景");

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
     * 私有构造函数
     *
     * @param code        枚举编码
     * @param description 描述说明
     */
    private AccountingErrScenarioEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * 通过枚举 <code>code</code>获得枚举
     *
     * @param code 枚举编码
     * @return 错误明明细枚举
     */
    public static AccountingErrScenarioEnum getByCode(String code) {
        for (AccountingErrScenarioEnum detailCode : values()) {
            if (detailCode.getCode().equals(code)) {
                return detailCode;
            }
        }
        return null;
    }

    // ~~~容器方法 ~~~

    /**
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * @return description
     */
    public String getDescription() {
        return description;
    }

}
