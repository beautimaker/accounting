/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.facade;

import com.xushicao.accounting.dao.entity.AccountDO;
import com.xushicao.accounting.facade.result.AccountResult;
import com.xushicao.accounting.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 账户操作门面接口
 *
 * @author Shichao.xu
 * @version $ AccountTradeFacade, V0.1 2024/4/29 19:36 Shichao.xu Exp $
 */
public interface AccountTradeFacade {

    /**
     * 账户取款接口
     * 通过传入账户账号和取款金额，
     * 使用交易模板检查参数
     * 使用交易模板实现取款服务
     * 返回结果
     *
     * @param accountNo
     * @param amount
     * @return
     */
    AccountResult withdraw(String accountNo, long amount);


    /**
     * 转账方法
     * 通过传入的转入账号和转出账号以及金额
     * 根据金额分别更新对应账户的余额，同时插入交易记录表，账户变动表
     *
     * @param accountFromNo 转出账号
     * @param AccountToNo   转入账号
     * @param amount        金额
     * @return 返回结果
     */
    AccountResult transfer(String accountFromNo, String AccountToNo, long amount);
}