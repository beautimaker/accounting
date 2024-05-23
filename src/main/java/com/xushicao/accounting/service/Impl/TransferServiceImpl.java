/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.service.Impl;

import com.xushicao.accounting.dao.entity.AccountDO;
import com.xushicao.accounting.facade.req.DepositReq;
import com.xushicao.accounting.facade.req.TransferReq;
import com.xushicao.accounting.model.enums.AccountingErrDtlEnum;
import com.xushicao.accounting.model.exception.AccountingException;
import com.xushicao.accounting.service.DepositService;
import com.xushicao.accounting.service.TransferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;

/**
 * 转账服务接口实现
 *
 * @author Shichao.xu
 * @version $ TransferServiceImpl, V0.1 2024/5/10 21:30 Shichao.xu Exp $
 */
@Service
public class TransferServiceImpl extends BaseService implements TransferService {

    /**
     * 日志对象
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TransferService.class);

    /**
     * 转账接口方法实现
     *
     * @param transferReq 交易请求
     */
    @Override
    public TransInfo transfer(TransferReq transferReq) {

        LOGGER.info("收到用户转账请求：转出账户{} 转入账户{}", transferReq.getAccountFromNo(), transferReq.getAccountToNo());

        //生成交易信息对象
        TransInfo transInfo = new TransInfo(transferReq);

        //完成交易
        transService(transInfo);


        LOGGER.info("用户转账请求处理结束：转出账户{} 转入账户{}", transferReq.getAccountFromNo(), transferReq.getAccountToNo());

        return transInfo;
    }

}