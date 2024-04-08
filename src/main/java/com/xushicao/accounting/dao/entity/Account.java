package com.xushicao.accounting.dao.entity;

import java.util.Date;

public class Account {
     private String account_no;

    private String account_name;

    private String account_type;

    private String status;

    private long balance;

    private Date last_trans_time;

    private Date create_time;

    private Date update_time;

    private String currency;

    public String getAccount_no() {
        return account_no;
    }

    public void setAccount_no(String account_no) {
        this.account_no = account_no;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
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
        return last_trans_time;
    }

    public void setLast_trans_time(Date last_trans_time) {
        this.last_trans_time = last_trans_time;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
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
                "account_no='" + account_no + '\'' +
                ", account_name='" + account_name + '\'' +
                ", account_type='" + account_type + '\'' +
                ", status='" + status + '\'' +
                ", balance=" + balance +
                ", last_trans_time=" + last_trans_time +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                ", currency='" + currency + '\'' +
                '}';
    }
}
