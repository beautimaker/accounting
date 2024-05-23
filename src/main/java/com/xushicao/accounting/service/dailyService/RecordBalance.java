/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.service.dailyService;

import com.xushicao.accounting.dao.entity.AccountDO;
import com.xushicao.accounting.dao.entity.AccountDailyDO;
import com.xushicao.accounting.dao.mapper.AccountDailyMapper;
import com.xushicao.accounting.dao.mapper.AccountMapper;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;


/**
 * 定时任务类
 *
 * @author Shichao.xu
 * @version $ RecordBalance, V0.1 2024/5/22 19:15 Shichao.xu Exp $
 */
@Service
public class RecordBalance implements Job {

    /**
     * 账户实体映射对象
     */
    @Autowired
    AccountMapper accountMapper;

    /**
     * 记账实体记录对象
     */
    @Autowired
    AccountDailyMapper accountDailyMapper;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        int pageNum = 0;
        int pageSize = 100;
        List<AccountDO> accountDOS;
        AccountDailyDO accountDailyDO = new AccountDailyDO();
        do {
            accountDOS = accountMapper.selectPage(pageNum, pageSize);
            for (AccountDO accountDO : accountDOS) {
                long days = LocalDateTime.now().until(accountDO.getLastTransTime(), ChronoUnit.DAYS);
                accountDailyMapper.update(accountDO, days);
            }
            pageNum++;
        } while (accountDOS.size() == pageSize);
    }
}