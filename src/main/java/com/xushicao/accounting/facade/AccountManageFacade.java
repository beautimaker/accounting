/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.facade;

import com.xushicao.accounting.facade.req.AccountManageReq;
import com.xushicao.accounting.facade.result.AccountResult;

/**
 * 开户接口<br/>
 * 方法：开户方法
 *
 * @author Shichao.xu
 * @version $ AccountManageFacade, V0.1 2024/4/8 12:56 Shichao.xu Exp $
 */

public interface AccountManageFacade {

    /**
     * 根据开户请求对象，通过用户信息
     * 生成用户账号，同时向数据库中插入数据记录
     * 并且返回用户账号到返回结果对象中
     * 返回对象中含有开户是否的成功的属性success
     *
     * @param accountManageReq 开户请求
     * @return 开户结果
     */
    AccountResult openAccount(AccountManageReq accountManageReq);

    /**
     * 冻结方法
     * 根据账户账号，修改数据库表中账户状态为”F“
     * 实现账户的冻结
     * 使用trade模板检查异常和建立返回结果
     *
     * @param accountNo 账户账号
     * @return 返回结果
     */
    AccountResult freezeAccount(String accountNo);


    /**
     * 解冻方法
     * 根据账户账号，修改数据库表中账户状态为”N“
     * 实现用户解冻
     * 使用trade模板检查异常和建立返回结果
     *
     * @param accountReq 用户请求
     * @return 返回结果
     */
    AccountResult unFreezeAccount(String accountReq);

    /**
     * 销户方法
     * 根据用户传入账号，修改数据库账户状态为"C"
     * 实现账户销户
     * 使用trade模板检查异常和建立返回结果
     *
     * @param accountNo 用户账号
     * @return 返回结果
     */
    AccountResult closeAccount(String accountNo);


}
