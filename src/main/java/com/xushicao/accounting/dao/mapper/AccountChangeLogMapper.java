/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.dao.mapper;

import com.xushicao.accounting.dao.entity.AccountChangeLogDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 账户修改记录表映射接口
 *
 * @author Shichao.xu
 * @version $ AccountChangeLogMapper, V0.1 2024/4/18 10:00 Shichao.xu Exp $
 */
@Mapper
public interface AccountChangeLogMapper {

    /**
     * 插入方法
     *
     * @param changeLogDO
     */
    void insert(AccountChangeLogDO changeLogDO);

}