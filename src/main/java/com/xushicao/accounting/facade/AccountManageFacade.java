/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.facade;
import com.xushicao.accounting.facade.req.OpenAccountReq;
import com.xushicao.accounting.facade.result.AccountManageResult;

/**
 * 开户接口<br/>
 *方法：开户方法
 *@author Shichao.xu
 *@version $ AccountManageFacade, V0.1 2024/4/8 12:56 Shichao.xu Exp $
 */

public interface AccountManageFacade {
    /**
     *根据开户请求对象，实现开户功能
     * @param openAccountReq 开户请求
     * @return 开户结果
     */
    AccountManageResult openAccount(OpenAccountReq openAccountReq);




}
