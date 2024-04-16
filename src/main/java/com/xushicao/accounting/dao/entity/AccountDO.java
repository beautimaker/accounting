/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.dao.entity;

import java.util.Date;

/**
 * 账户实体类
 * 用于对接数据库数据
 *
 * @author Shichao.xu
 * @version $ Account, V0.1 2024/4/8 12:56 Shichao.xu Exp $
 */

public class AccountDO {
    /**
     * 账号
     */
    private String accountNo;

    /**
     * 账户名
     */
    private String accountName;

    /**
     * 账户类型
     */
    private String accountType;

    /**
     * 账户状态
     */
    private String status;

    /**
     * 存款
     */
    private long balance;

    /**
     * 修改时间
     */
    private Date lastTransTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 币种
     */
    private String currency;

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String account_no) {
        this.accountNo = account_no;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String account_name) {
        this.accountName = account_name;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String account_type) {
        this.accountType = account_type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public Date getLast_trans_time() {
        return lastTransTime;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Account{" +
                "account_no='" + accountNo + '\'' +
                ", account_name='" + accountName + '\'' +
                ", account_type='" + accountType + '\'' +
                ", status='" + status + '\'' +
                ", balance=" + balance +
                ", last_trans_time=" + lastTransTime +
                ", create_time=" + createTime +
                ", update_time=" + updateTime +
                ", currency='" + currency + '\'' +
                '}';
    }
}
