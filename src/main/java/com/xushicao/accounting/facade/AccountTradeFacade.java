/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.facade;

import com.xushicao.accounting.facade.req.DepositReq;
import com.xushicao.accounting.facade.req.TransferReq;
import com.xushicao.accounting.facade.req.WithdrawReq;
import com.xushicao.accounting.facade.result.*;

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
     * @param withdrawReq 取款请求
     * @return 返回结果
     */
    BaseResult withdraw(WithdrawReq withdrawReq);


    /**
     * 转账方法
     * 通过传入的转入账号和转出账号以及金额
     * 根据金额分别更新对应账户的余额，同时插入交易记录表，账户变动表
     *
     * @param transferReq 转账请求
     * @return 返回结果
     */
    BaseResult transfer(TransferReq transferReq);

    /**
     * 存款方法
     * 通过账号账号, 判断账号是是企业用户还是
     * 个人用户，根据存款金额同时添加到账户和总账账户中
     * 并返回存款结果
     *
     * @param depositReq 存款请求
     * @return 返回结果
     */
    BaseResult deposit(DepositReq depositReq);
}