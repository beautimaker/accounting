/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.facade.result;

import com.xushicao.accounting.common.ErrorContext;

/**
 * 结果抽象基类
 *
 * @author Shichao.xu
 * @version $ BaseResult, V0.1 2024/5/14 14:51 Shichao.xu Exp $
 */
public abstract class BaseResult {

    /**
     * 错误码上下文
     * <li>01-用户请求为空</li>
     * <li>02-内部户别名为空</li>
     * <li>03-用户类型或者币种不在给定范围内</>
     */
    private ErrorContext errorContext;

    /**
     * 成功标记<br>
     * 默认为false
     */
    private boolean success = false;

    public ErrorContext getErrorContext() {
        return errorContext;
    }

    public void setErrorContext(ErrorContext errorContext) {
        this.errorContext = errorContext;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}