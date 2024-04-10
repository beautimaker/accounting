/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.dao.mapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
/**
 * 序列生成接口<br/>
 * 生成序列号
 *@author Shichao.xu
 *@version $SequenceMapper, V0.1 2024/4/8 12:56 Shichao.xu Exp $
 */
@Mapper
public interface SequenceMapper {
    /**
     * 序列生成函数<br/>
     * 根据传入参数指定数据库中的生成序列参数记录，生成序列号
     * @param name   指定的参数
     * @return        序列号
     */
    @Select("SELECT nextval(#{name})")
    long getNextVal(@Param("name")String name);

}
