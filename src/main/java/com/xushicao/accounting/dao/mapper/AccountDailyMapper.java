/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.dao.mapper;

import com.xushicao.accounting.dao.entity.AccountDO;
import com.xushicao.accounting.dao.entity.AccountDailyDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 记账映射接口
 *
 * @author Shichao.xu
 * @version $ AccountDailyMapper, V0.1 2024/5/22 18:16 Shichao.xu Exp $
 */
@Mapper
public interface AccountDailyMapper {

    /**
     * 插入函数
     *
     * @param accountDailyDO 记账实体对象
     */
    void insert(AccountDailyDO accountDailyDO);

    /**
     * 更新函数
     *
     * @param accountDO 账户实体对象
     * @param days      当前时间与最后记账时间的天数差
     * @return 更新的数据条数
     */
    int update(AccountDO accountDO, long days);

}