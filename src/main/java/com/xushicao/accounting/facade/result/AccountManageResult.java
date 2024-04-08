/**
 * xxx.com Inc.
 *
 */
package com.xushicao.accounting.facade.result;

/**
 * 账户结果类
 *
 * <p>作为开户方法的返回值，返回的账户的开户结果<p/>
 *
 * @author  Shichao.Xu
 * @version  001
 */
public class AccountManageResult {

    /**
     * 账号生成规则：固定前缀+账号类型+序列号+币种<br/>
     * 示例：2088 01 12345678 0156<br/>
     */
    private String accountNo;

    /**
     * 错误码
     *<li>01-内部户别名为空</li>
     */

    private String errorCode;

    /**
     * 成功标记<br>
     * 默认为false
     */
    private boolean success = false;

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
