/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.template;

/**
 * 查询模板回调接口
 *
 * @author Shichao.xu
 * @version $ QueryCallBack, V0.1 2024/4/29 22:12 Shichao.xu Exp $
 */
public interface QueryCallBack {

    /**
     * 检查输入参数
     */
    void checkParameter();

    /**
     * 执行
     */
    void doQuery();

}