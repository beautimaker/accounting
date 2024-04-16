/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.common;

/**
 * @author Shichao.xu
 * @version $ ErrorContext, V0.1 2024/4/14 18:53 Shichao.xu Exp $
 */

public class ErrorContext {

    /**
     * 标准错误码
     */
    private String codeStr;

    /**
     * 错误描述
     */
    private String Desc;

    public String getCodeStr() {
        return codeStr;
    }

    public void setCodeStr(String codeStr) {
        this.codeStr = codeStr;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }
}