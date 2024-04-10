/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.domain.enums;

/**
 * @author Shichao.xu
 * @version $ AccountTypeEnum, V0.1 2024/4/8 22:32 Shichao.xu Exp $
 */
public enum AccountTypeEnum {
    PERSONAL_ACCOUNT("01", "个人账户"),
    COMPANY_ACCOUNT("02", "公司账户"),
    INNER_ACCOUNT("03", "内部户");

    /**
     * 枚举编码
     */
    private final String code;

    /**
     * 描述说明
     */
    private final String description;

    private AccountTypeEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * 通过枚举<code>code</code>获得枚举
     *
     * @param code 枚举编码
     * @return 支付错误明细枚举
     */
    public static AccountTypeEnum getByCode(String code) {
        for (AccountTypeEnum detailCode : values()) {
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