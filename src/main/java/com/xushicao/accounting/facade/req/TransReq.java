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
    protected long amount;

    /**
     * 操作员ID
     */
    protected String operatorID;

    /**
     * 订单号
     */
    protected String orderNo;

    /**
     * 核算机构
     */
    protected String reconInst;

    /**
     * 交易时间
     */
    protected LocalDateTime transDT;

    /**
     * 交易代码
     * DEP-存款
     * WDL-取款
     * XFR-转账
     */
    protected String TransCode;

    /**
     * 交易子代码
     * 01-现金存款，现金取款，同行转账
     * 02-支票存款，支票取款，跨行转账
     * 03-电子转账存款，ATM取款，境外转账
     */
    protected String subTransCode;

    /**
     * 外部业务时间
     */
    protected LocalDateTime outDate;


    /**
     * 币种
     */
    protected String currency;

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