/**
 * xxx.com Inc.
 *
 */
package com.xushicao.accounting.service;

import com.xushicao.accounting.dao.entity.Account;
import com.xushicao.accounting.dao.mapper.AccountMapper;
import com.xushicao.accounting.facade.AccountManageFacade;
import com.xushicao.accounting.facade.req.OpenAccountReq;
import com.xushicao.accounting.facade.result.AccountManageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 账户管理接口实现类<br/>
 *
 *用于实现账户管理接口
 *
 * @author   Shichao.Xu
 * @version  001
 */

@Service
public class AccountManageFacadeImpl implements AccountManageFacade {

    @Autowired
    AccountMapper accountMapper; //自动注入accountMapper对象

    /**
     * 开户方法的重写<br/>
     * <p>
     * 完成开户
     *
     * @param openAccountReq 开户请求
     * @return
     */
    @Override
    public AccountManageResult openAccount(OpenAccountReq openAccountReq) {

        AccountManageResult accountManageResult = new AccountManageResult();//建立一个返回对象
        // 1、参数校验,判断账户类型为内部户时，账户名是否为空
        if (openAccountReq.getAccountType() == "03") {
            if (openAccountReq.getAccountName() == null) {
                accountManageResult.setErrorCode("01");//设置错误码
                accountManageResult.setSuccess(false);
                return accountManageResult;
            }
        }

        // 2、生成账号
        String accountType = openAccountReq.getAccountType();//获取账号类型
        String accountCurrency = openAccountReq.getCurrency();//获取账号币种
        String serialNo=Long.toString(accountMapper.getSerialNo()); //获取序列号sequenceMapper.getNextVal("my_sequence")
        String accountNo = "2000" + accountType + serialNo + accountCurrency;

        // 3、数据库插入
        Account account = new Account();
        account.setAccount_no(accountNo);
        account.setAccount_name(openAccountReq.getAccountName());
        account.setAccount_type(openAccountReq.getAccountType());
        account.setStatus("N");
        account.setBalance(0);
        Date date = new Date();

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        account.setCreate_time(date);
        account.setUpdate_time(date);
        account.setCurrency(openAccountReq.getCurrency());
        accountMapper.insert(account);

        // 4、返回开户结果
        accountManageResult.setAccountNo(accountNo);
        accountManageResult.setSuccess(true);
        return accountManageResult;
    }
}
