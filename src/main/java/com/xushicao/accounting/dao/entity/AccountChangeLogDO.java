/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.dao.entity;

/**
 * 账户修改记录表实体类
 *
 * @author Shichao.xu
 * @version $ AccountChangeLogDO, V0.1 2024/4/18 9:56 Shichao.xu Exp $
 */

import java.util.Date;

public class AccountChangeLogDO {

    /**
     * 用户账号
     */
    private String accountNo;

    /**
     * 修改类型
     */
    private String changeType;

    /**
     * 变更前状态
     */
    private String before;

    /**
     * 变更后状态
     */
    private String after;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
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

    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }

    public String getBefore() {
        return before;
    }

    public void setBefore(String before) {
        this.before = before;
    }

    public String getAfter() {
        return after;
    }

    public void setAfter(String after) {
        this.after = after;
    }
}