/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */

package com.xushicao.accounting.dao.mapper;

import com.xushicao.accounting.dao.entity.AccountDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 账户映射接口
 * 方法：数据插入方法
 *
 * @author Shichao.xu
 * @version $  AccountMapper, V0.1 2024/4/8 12:56 Shichao.xu Exp $
 */
@Mapper
public interface AccountMapper {

    /**
     * 插入方法
     * 通过给定实体类，向数据库中插入数据
     *
     * @param accountdo 数据库实体类
     */
    void insert(AccountDO accountdo);

    AccountDO select();
}

