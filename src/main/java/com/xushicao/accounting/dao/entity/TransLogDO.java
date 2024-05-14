/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.dao.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 交易记录表实体类
 *
 * @author Shichao.xu
 * @version $ TransLogDO, V0.1 2024/4/25 20:39 Shichao.xu Exp $
 */
public class TransLogDO {

    /**
     * 交易记录ID
     */
    private String id;

    /**
     * 借方账号
     */
    private String debitAccountNo;

    /**
     * 贷方账号
     */
    private String creditAccountNo;

    /**
     * 交易金额
     */
    private long transAmount;

    /**
     * 币种
     */
    private String currency;

    /**
     * 交易代码
     * DEP-存款
     * WDL-取款
     * XFR-转账
     */
    private String transCode;

    /**
     * 交易子代码
     * 01-现金存款，现金取款，同行转账
     * 02-支票存款，支票取款，跨行转账
     * 03-电子转账存款，ATM取款，境外转账
     */
    private String subTransCode;

    /**
     * 交易日期
     */
    private LocalDate transDate;

    /**
     * 交易时间
     */
    private LocalDateTime transDT;

    /**
     * 外部业务时间
     */
    private LocalDateTime outDate;

    /**
     * 业务编号
     */
    private String bizNo;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 核算机构
     */
    private String reconInst;

    /**
     * 记账备注
     */
    private String memo;

    /**
     * 操作员ID
     */
    private String operatorID;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 修改时间
     */
    private LocalDateTime gmtModified;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTransCode() {
        return transCode;
    }

    public void setTransCode(String transCode) {
        this.transCode = transCode;
    }

    public String getSubTransCode() {
        return subTransCode;
    }

    public void setSubTransCode(String subTransCode) {
        this.subTransCode = subTransCode;
    }

    public LocalDate getTransDate() {
        return transDate;
    }

    public void setTransDate(LocalDate transDate) {
        this.transDate = transDate;
    }

    public LocalDateTime getTransDT() {
        return transDT;
    }

    public void setTransDT(LocalDateTime transDT) {
        this.transDT = transDT;
    }

    public LocalDateTime getOutDate() {
        return outDate;
    }

    public void setOutDate(LocalDateTime outDate) {
        this.outDate = outDate;
    }

    public String getBizNo() {
        return bizNo;
    }

    public void setBizNo(String bizNo) {
        this.bizNo = bizNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getReconInst() {
        return reconInst;
    }

    public void setReconInst(String reconInst) {
        this.reconInst = reconInst;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getOperatorID() {
        return operatorID;
    }

    public void setOperatorID(String operatorID) {
        this.operatorID = operatorID;
    }

    public LocalDateTime getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(LocalDateTime gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public LocalDateTime getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(LocalDateTime gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getDebitAccountNo() {
        return debitAccountNo;
    }

    public void setDebitAccountNo(String debitAccountNo) {
        this.debitAccountNo = debitAccountNo;
    }

    public String getCreditAccountNo() {
        return creditAccountNo;
    }

    public void setCreditAccountNo(String creditAccountNo) {
        this.creditAccountNo = creditAccountNo;
    }

    public long getTransAmount() {
        return transAmount;
    }

    public void setTransAmount(long transAmount) {
        this.transAmount = transAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}