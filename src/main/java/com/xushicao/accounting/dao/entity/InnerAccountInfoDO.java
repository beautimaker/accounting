/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.dao.entity;

import java.util.Date;

/**
 * 内部户扩展表实体类
 *
 * @author Shichao.xu
 * @version $ InnerAccountInfoDO, V0.1 2024/4/11 19:53 Shichao.xu Exp $
 */

public class InnerAccountInfoDO {
    /**
     * 内部户账号
     */
    private String accountNo;

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

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}