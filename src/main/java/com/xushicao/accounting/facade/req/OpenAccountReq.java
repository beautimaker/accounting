/**
 * xxx.com Inc.
 *
 */
package com.xushicao.accounting.facade.req;


/**
 *开户请求类
 *<p>保存用户信息，，用于之后结果对象的创建</p>
 *
 * @author Shichao.Xu
 * @version 001
 */

public class OpenAccountReq {

    /**
     * 账号别名，选填，内部户（账户类型为03）时必填
     */
    private String accountName;

    /**
     * 账户类型
     * <li>01-个人账户</li>
     * <li>02-对公账户</li>
     * <li>03-内部户</li>
     */
    private String accountType;

    /**
     *币种
     * <li>001-人民币</li>
     * <li>002-美元</li>
     * <li>003-欧元</li>
     */
    private String currency;



    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
