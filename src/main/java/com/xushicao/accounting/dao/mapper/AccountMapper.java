/**
 * xxx.com Inc.
 *
 */
package com.xushicao.accounting.dao.mapper;

import com.xushicao.accounting.dao.entity.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;


/**
 *
 */

@Mapper
public interface AccountMapper {

    /**
     * 插入方法
     * 通过给定实体类，向数据库中插入数据
     *
     * @param account 数据库实体类
     */
    @Insert("INSERT INTO account values (#{account_no},#{account_name},#{account_type},#{status},#{balance},#{last_trans_time},now(),#{update_time},#{currency})")
    void insert(Account account);
}

