/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.domain.enums;

/**
 * @author Shichao.xu
 * @version $ AccountCurrencyEnum, V0.1 2024/4/8 23:32 Shichao.xu Exp $
 */

public enum AccountCurrencyEnum {
    CHN_CURRENCY("156", "人民币"),

    USA_CURRENCY("840", "美元"),

    EUR_CURRENCY("978", "欧元");

    /**
     * 枚举编码
     */
    private final String code;

    /**
     * 描述说明
     */
    private final String description;

    private AccountCurrencyEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * 通过枚举<code>code</code>获得枚举
     *
     * @param code 枚举编码
     * @return 支付错误明细枚举
     */
    public static AccountCurrencyEnum getByCode(String code) {
        for (AccountCurrencyEnum detailCode : values()) {
            if (detailCode.getCode().equals(code)) {
                return detailCode;
            }
        }
        return null;
    }

    /**
     * @return Returns the code.
     */
    public String getCode() {
        return code;
    }

}