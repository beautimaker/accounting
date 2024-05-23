/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.dao.entity;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
     * 最后记账时间
     */
    private LocalDateTime lastTransTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 币种
     */
    private String currency;

    /**
     * 余额方向
     */
    private String direction;

    /**
     * 上期余额
     */
    private long prevBalance;

    /**
     * 上期交易日
     */
    private LocalDate prevTransDate;

    public long getPrevBalance() {
        return prevBalance;
    }

    public void setPrevBalance(long prevBalance) {
        this.prevBalance = prevBalance;
    }

    public LocalDate getPrevTransDate() {
        return prevTransDate;
    }

    public void setPrevTransDate(LocalDate prevTransDate) {
        this.prevTransDate = prevTransDate;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public LocalDateTime getLastTransTime() {
        return lastTransTime;
    }

    public void setLastTransTime(LocalDateTime lastTransTime) {
        this.lastTransTime = lastTransTime;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
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
