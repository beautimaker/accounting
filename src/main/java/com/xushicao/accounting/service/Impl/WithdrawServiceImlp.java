/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.service.Impl;

import com.xushicao.accounting.facade.req.WithdrawReq;
import com.xushicao.accounting.service.WithdrawService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 取款服务接口实现
 *
 * @author Shichao.xu
 * @version $ WithdrawServiceImlp, V0.1 2024/5/10 21:29 Shichao.xu Exp $
 */
@Service
public class WithdrawServiceImlp extends BaseService implements WithdrawService {

    /**
     * 日志对象
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(WithdrawService.class);

    /**
     * 取款实现方法
     *
     * @param withdrawReq 交易请求
     */
    @Override
    public void withdraw(WithdrawReq withdrawReq) {

        //打印日志
        LOGGER.info("收到用户存款请求。账号：{}，存款：{}", withdrawReq.getAccountNo(), withdrawReq.getAmount());


        //生成交易信息对象
        BaseService.TransInfo transInfo = new TransInfo(withdrawReq);

        //完成交易
        tranService(transInfo);

        //打印日志
        LOGGER.info("用户存款请求处理结束。账号：{}，存款：{}", withdrawReq.getAccountNo(), withdrawReq.getAmount());
    }
}