/**
 * xxx.com Inc.
 *
 */
package com.xushicao.accounting.facade;

import com.xushicao.accounting.facade.req.OpenAccountReq;
import com.xushicao.accounting.facade.result.AccountManageResult;

/**
 *账户管理接口<br/>
 *<br/>包含如下方法
 *<br/> 开户：实现账户开户功能
 *
 * @author Shichao.Xu
 * @version 001
 */
public interface AccountManageFacade {
    /**
     *根据开户请求对象，实现开户功能
     * @param openAccountReq 开户请求
     * @return 开户结果
     */
    AccountManageResult openAccount(OpenAccountReq openAccountReq);
}
