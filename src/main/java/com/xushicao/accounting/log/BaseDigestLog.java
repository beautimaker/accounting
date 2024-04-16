/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.log;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xushicao.accounting.common.ErrorContext;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Shichao.xu
 * @version $ BaseDigestLog, V0.1 2024/4/14 21:10 Shichao.xu Exp $
 */

public abstract class BaseDigestLog {

    /**
     * 调用成功的字符串值
     */
    protected static final String TRUE_VALUE = "Y";

    /**
     * 调用失败的字符串值
     */
    protected static final String FALSE_VALUE = "N";

    /**
     * 调用成功的字符串描述
     */
    protected static final String TRUE_DESC = "SUCCESS";

    /**
     * 分隔符
     */
    protected static final String SPLIT_TAG = ",";

    /**
     * 默认值
     */
    protected static final String DEFAULT_VALUE = "-";

    /**
     * 被调用方法
     */
    protected String invocationMethod;

    /**
     * 操作是否成功
     */
    protected boolean success;

    /**
     * 错误上下文
     */
    protected ErrorContext errorContext;

    /**
     * 操作耗时
     */
    protected long elapse;

    /**
     * 转换为摘要日志字符串
     *
     * @return
     */
    public String toDigistLog() {
        Map<String, Object> log = new HashMap<>();
        log.put("time", new SimpleDateFormat().format(new Date()));
        log.put("thread", Thread.currentThread().getName());
        {
            //构造调用信息

            //构造事务信息
            logComposeBizInfo(log);
            //构造交易信息
            logComposeTransInfo(log);
            //构造调用方信息
        }
        try {
            return new ObjectMapper().writeValueAsString(log);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 组装事务信息
     *
     * @param log 日志信息
     */
    void logComposeTransInfo(Map<String, Object> log) {
        Map<String, String> logInfo = new HashMap<>();
        composeTransInfo(logInfo);
        if (logInfo != null && logInfo.size() > 0) {
            log.put("transInfo", logInfo);
        }
    }

    protected void composeTransInfo(Map<String, String> log) {
    }

    /**
     * 组装交易信息
     *
     * @param log 日志信息
     */
    void logComposeBizInfo(Map<String, Object> log) {
        Map<String, String> logInfo = new HashMap<>();
        composeBizInfo(logInfo);
        if (logInfo != null && logInfo.size() > 0) {
            log.put("bizInfo", logInfo);
        }
    }

    protected void composeBizInfo(Map<String, String> log) {
    }

//    /**
//     * 构造调用者信息。
//     *
//     * @param log 日志json map
//     */
//    final protected void composeCallerInfo(Map<String, Object> log) {
//        String ip = TechplayStringUtil.defaultIfBlank(XFRpcUtil.getClientIp(), DEFAULT_VALUE);
//        String traceId = TechplayStringUtil.defaultIfBlank(XFRpcUtil.getTraceId(), DEFAULT_VALUE);
//        String clientDomain = TechplayStringUtil.defaultIfBlank(XFRpcUtil.getClientDomain(), DEFAULT_VALUE);
//        log.put("clientIp", ip);
//        log.put("clientName", clientDomain);
//        log.put("traceId", traceId);
//    }

    //构造调用信息


    //内部方法
    private String fetchErrorDetailCode(ErrorContext errorContext) {
        if (errorContext == null) {
            return DEFAULT_VALUE;
        }
        return null;
    }


    public static void main(String[] args) {

    }
}