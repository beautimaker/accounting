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

    /**
     * 插入方法
     *
     * @param transLogDO 交易记录实体对象
     */
    void insert(TransLogDO transLogDO);

    /**
     * 查询方法
     *
     * @param orderNo 订单号
     */
    String select(String orderNo);


}