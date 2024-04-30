/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.dao.mapper;

import com.xushicao.accounting.dao.entity.AccountLogDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 账户余额变动表映射接口
 *
 * @author Shichao.xu
 * @version $ AccountLogMapper, V0.1 2024/4/26 13:25 Shichao.xu Exp $
 */
@Mapper
public interface AccountLogMapper {

    /**
     * 账户变动插入方法
     * 通过传入的账户变动实体，向数据库中插入数据
     *
     * @param accountLogDO
     */
    void insert(AccountLogDO accountLogDO);

}