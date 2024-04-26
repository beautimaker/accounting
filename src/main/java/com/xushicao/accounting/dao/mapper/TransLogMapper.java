/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.dao.mapper;

import com.xushicao.accounting.dao.entity.AccountLogDO;
import com.xushicao.accounting.dao.entity.TransLogDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 交易记录表映射接口
 *
 * @author Shichao.xu
 * @version $ TransLogMapper, V0.1 2024/4/26 15:32 Shichao.xu Exp $
 */
@Mapper
public interface TransLogMapper {

    void insert(TransLogDO transLogDO);

}