/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */

package com.xushicao.accounting.service;

import com.xushicao.accounting.dao.entity.AccountDO;
import com.xushicao.accounting.dao.mapper.AccountMapper;
import com.xushicao.accounting.dao.mapper.SequenceMapper;
import com.xushicao.accounting.domain.enums.AccountCurrencyEnum;
import com.xushicao.accounting.domain.enums.AccountTypeEnum;
import com.xushicao.accounting.facade.AccountManageFacade;
import com.xushicao.accounting.facade.req.OpenAccountReq;
import com.xushicao.accounting.facade.result.AccountManageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

/**
 * 开户接口实现类
 * 属性：开户映射接口、序列号生成接口
 * 方法：开户方法、 账户实体生成方法、账户生成方法
 *
 * @author Shichao.xu
 * @version $ AccountManageFacadeImpl, V0.1 2024/4/8 12:56 Shichao.xu Exp $
 */
@RestController
@RequestMapping("/")
public class AccountManageFacadeImpl implements AccountManageFacade {

    /**
     * 开户映射接口
     * 用于实现插入开户数据到mysql
     */
    @Autowired
    private AccountMapper accountMapper;

    /**
     * 序列号映射接口
     * 用于生成序列号
     */
    @Autowired
    private SequenceMapper sequenceMapper;

    /**
     * 开户方法的重写<br/>
     * 用于实现开户
     *
     * @param openAccountReq 开户请求
     * @return 开户结果
     */
    @Override
    @PostMapping("account")
    public AccountManageResult openAccount(@RequestBody OpenAccountReq openAccountReq) {

        AccountManageResult accountManageResult = new AccountManageResult();//建立一个返回对象

        // 1、参数校验
        //判断开户请求对象是否为空
        if (openAccountReq != null) {
            //判断币种和账户类型是否不在给定范围内
            if (AccountTypeEnum.getByCode(openAccountReq.getAccountType()) != null
                    && AccountCurrencyEnum.getByCode(openAccountReq.getCurrency()) != null) {
                //判断账户类型为内部户时，账户名是否为空
                if (openAccountReq.getAccountType().equals("03")) {
                    if (openAccountReq.getAccountName() == null) {
                        accountManageResult.setErrorCode("02");
                        accountManageResult.setSuccess(false);
                        return accountManageResult;
                    }
                }

                accountManageResult.setSuccess(true);
            } else {
                accountManageResult.setErrorCode("03");
                accountManageResult.setSuccess(false);
                return accountManageResult;
            }
        } else {
            accountManageResult.setErrorCode("01");
            accountManageResult.setSuccess(false);
            return accountManageResult;
        }

        // 2、生成账号
        String accountNo = getAccountNo(openAccountReq);

        // 3、数据库插入
        AccountDO accountdo = buildAccount(accountNo, openAccountReq);
        accountMapper.insert(accountdo);

        // 4、返回开户结果
        accountManageResult.setAccountNo(accountNo);
        accountManageResult.setSuccess(true);
        return accountManageResult;
    }

    /**
     * 账号生成方法
     * 根据客户请求，生活对应账号
     *
     * @param openAccountReq 客户请求
     * @return 账号
     */
    private String getAccountNo(OpenAccountReq openAccountReq) {

        String accountType = openAccountReq.getAccountType();//获取账号类型
        AccountTypeEnum.getByCode(accountType);
        String accountCurrency = openAccountReq.getCurrency();//获取账号币种
        String serialNo = Long.toString(sequenceMapper.getNextVal("account_seq")); //获取序列号sequenceMapper.getNextVal("my_sequence")
        String accountNo = "2000" + accountType + serialNo + accountCurrency;
        return accountNo;

    }

    /**
     * 账户实体生成方法
     * 构建账户实体对象
     *
     * @param accountNo      账号对象
     * @param openAccountReq 用户请求对象
     * @return 账户实体对象
     */
    private AccountDO buildAccount(String accountNo, OpenAccountReq openAccountReq) {

        AccountDO accountdo = new AccountDO();
        accountdo.setAccountNo(accountNo);
        accountdo.setAccountName(openAccountReq.getAccountName());
        accountdo.setAccountType(openAccountReq.getAccountType());
        accountdo.setStatus("N");
        accountdo.setBalance(0);
        accountdo.setCurrency(openAccountReq.getCurrency());
        return accountdo;

    }


}
