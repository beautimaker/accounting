/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.service.Impl;

import com.xushicao.accounting.dao.entity.AccountDO;
import com.xushicao.accounting.facade.req.DepositReq;
import com.xushicao.accounting.model.enums.AccountingErrDtlEnum;
import com.xushicao.accounting.model.exception.AccountingException;
import com.xushicao.accounting.service.DepositService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;

/**
 * 存款服务接口实现
 *
 * @author Shichao.xu
 * @version $ DepositService, V0.1 2024/5/10 21:28 Shichao.xu Exp $
 */
@Service
public class DepositServiceImpl extends BaseService implements DepositService {

    /**
     * 日志对象
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DepositService.class);

    /**
     * 存款方法重写
     */
    @Override
    public TransInfo deposit(DepositReq depositReq) {

        //打印日志
        LOGGER.info("收到用户存款请求。账号：{}，存款：{}", depositReq.getAccountNo(), depositReq.getAmount());


        //生成交易信息对象
        TransInfo transInfo = new TransInfo(depositReq);

        //完成交易
        transService(transInfo);

        //打印日志
        LOGGER.info("用户存款请求处理结束。账号：{}，存款：{}", depositReq.getAccountNo(), depositReq.getAmount());

        return transInfo;
    }
}