/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.facade;

import com.xushicao.accounting.facade.req.AccountReq;
import com.xushicao.accounting.facade.result.AccountManageResult;

/**
 * 开户接口<br/>
 * 方法：开户方法
 *
 * @author Shichao.xu
 * @version $ AccountManageFacade, V0.1 2024/4/8 12:56 Shichao.xu Exp $
 */

public interface AccountManageFacade {
    /**
     * 根据开户请求对象，实现开户功能
     *
     * @param accountReq 开户请求
     * @return 开户结果
     */
    AccountManageResult openAccount(AccountReq accountReq);


    /**
     * 根据账号账号，实现冻结功能
     *
     * @param accountReq
     * @return
     */
    AccountManageResult freezeAccount(AccountReq accountReq);


    /**
     * 根据账户账号，实现解冻功能
     *
     * @param accountReq
     * @return
     */
    AccountManageResult unFreezeAccount(AccountReq accountReq);

}
