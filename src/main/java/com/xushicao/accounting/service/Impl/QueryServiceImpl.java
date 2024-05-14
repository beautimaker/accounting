/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.service.Impl;

import com.xushicao.accounting.dao.entity.AccountDO;
import com.xushicao.accounting.service.QueryService;
import com.xushicao.accounting.template.QueryTemplate;
import org.springframework.stereotype.Service;

/**
 * @author Shichao.xu
 * @version $ QueryServiceImpl, V0.1 2024/5/14 15:16 Shichao.xu Exp $
 */
@Service
public class QueryServiceImpl extends BaseService implements QueryService {


    /**
     * @param accountNo 账号
     * @return
     */
    @Override
    public AccountDO queryAccount(String accountNo) {

        LOGGER.info("收到用户查询账户请求：账号{}", accountNo);

        AccountDO accountDO = accountMapper.select(accountNo);

        LOGGER.info("用户查询账户请求处理结束：账号{}", accountNo);

        return accountDO;


    }

}