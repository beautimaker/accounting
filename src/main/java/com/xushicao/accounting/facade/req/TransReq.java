/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.facade.req;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Shichao.xu
 * @version $ transReq, V0.1 2024/5/14 15:55 Shichao.xu Exp $
 */
public abstract class TransReq {


    /**
     * 金额数
     */
    private long amount;

    /**
     * 操作员ID
     */
    private String operatorID;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 核算机构
     */
    private String reconInst;

    /**
     * 交易日期
     */
    private LocalDate transDate;

    /**
     * 交易时间
     */
    private LocalDateTime transDT;

    /**
     * 交易代码
     * DEP-存款
     * WDL-取款
     * XFR-转账
     */
    private String TransCode;

    /**
     * 交易子代码
     * 01-现金存款，现金取款，同行转账
     * 02-支票存款，支票取款，跨行转账
     * 03-电子转账存款，ATM取款，境外转账
     */
    private String subTransCode;

    /**
     * 外部业务时间
     */
    private LocalDateTime outDate;

    /**
     * 币种
     */
    private String currency;

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getOperatorID() {
        return operatorID;
    }

    public void setOperatorID(String operatorID) {
        this.operatorID = operatorID;
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

    public String getTransCode() {
        return TransCode;
    }

    public void setTransCode(String transCode) {
        TransCode = transCode;
    }

    public String getSubTransCode() {
        return subTransCode;
    }

    public void setSubTransCode(String subTransCode) {
        this.subTransCode = subTransCode;
    }

    public LocalDateTime getOutDate() {
        return outDate;
    }

    public void setOutDate(LocalDateTime outDate) {
        this.outDate = outDate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}