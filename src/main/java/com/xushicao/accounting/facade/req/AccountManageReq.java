/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.facade.req;


/**
 * 用户请求类
 * 属性：别名、类型、币种
 * 方法：get、set方法
 *
 * @author Shichao.xu
 * @version $ OpenAccountReq, V0.1 2024/4/8 12:56 Shichao.xu Exp $
 */

public class AccountManageReq {

    /**
     * 用户账号
     */
    private String accountNo;

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
     * 币种枚举类<br/>
     * 156-人民币<br/>
     * 840-美元<<br/>
     * 978-欧元<br/>
     */
    private String currency;

    /**
     * 科目
     */
    private String titleCode;

    /**
     * 核算机构
     */
    private String reconInst;

    /**
     * 外部信息关联代码
     */
    private String relationCode;

    /**
     * 外部关联机构ID
     */
    private String relationInstId;


    public String getTitleCode() {
        return titleCode;
    }

    public void setTitleCode(String titleCode) {
        this.titleCode = titleCode;
    }

    public String getReconInst() {
        return reconInst;
    }

    public void setReconInst(String reconInst) {
        this.reconInst = reconInst;
    }

    public String getRelationCode() {
        return relationCode;
    }

    public void setRelationCode(String relationCode) {
        this.relationCode = relationCode;
    }

    public String getRelationInstId() {
        return relationInstId;
    }

    public void setRelationInstId(String relationInstId) {
        this.relationInstId = relationInstId;
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

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    @Override
    public String toString() {
        return "OpenAccountReq{" +
                "accountName='" + accountName + '\'' +
                ", accountType='" + accountType + '\'' +
                ", currency='" + currency + '\'' +
                ", titleCode='" + titleCode + '\'' +
                ", reconInst='" + reconInst + '\'' +
                ", relationCode='" + relationCode + '\'' +
                ", relationInstId='" + relationInstId + '\'' +
                '}';
    }
}
