/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.dao.mapper;

import com.xushicao.accounting.dao.entity.InnerAccountInfoDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 内部户扩展表映射接口
 *
 * @author Shichao.xu
 * @version $ InnerAccountInfoMapper, V0.1 2024/4/11 20:05 Shichao.xu Exp $
 */
@Mapper
public interface InnerAccountInfoMapper {
    void insert(InnerAccountInfoDO innerAccountInfoDO);


}