/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.template;

/**
 * 查询模版回调接口
 * * @author Shichao.xu
 *
 * @version $ QueryCallBcak, V0.1 2024/4/14 14:33 Shichao.xu Exp $
 */

public interface TradeCallBack {

    /**
     * 检查输入参数
     */
    void checkParameter();

    /**
     * 执行开户
     */
    void doTrade();


    /**
     * 创建摘要日志
     *
     * @return 摘要日志
     */
//    QueryDigestLog buildDigestLog();

}