/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */

package com.xushicao.accounting.service;

import com.xushicao.accounting.common.AccountingConstants;
import com.xushicao.accounting.dao.entity.AccountDO;
import com.xushicao.accounting.dao.entity.InnerAccountInfoDO;
import com.xushicao.accounting.dao.mapper.AccountMapper;
import com.xushicao.accounting.dao.mapper.InnerAccountInfoMapper;
import com.xushicao.accounting.dao.mapper.SequenceMapper;
import com.xushicao.accounting.facade.AccountManageFacade;
import com.xushicao.accounting.facade.req.OpenAccountReq;
import com.xushicao.accounting.facade.result.AccountManageResult;
import com.xushicao.accounting.log.DigestLogAnnotated;
import com.xushicao.accounting.template.TradeCallBack;
import com.xushicao.accounting.template.TradeTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import static com.xushicao.accounting.util.ParaCheckUtil.*;

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
     * 内部会扩展表映射接口
     * 用于实现插入内部户扩展表数据到mysql
     */
    @Autowired
    private InnerAccountInfoMapper innerAccountInfoMapper;

    /**
     * 开户方法的重写<br/>
     * 用于实现开户
     *
     * @param openAccountReq 开户请求
     * @return 开户结果
     */
    @Override
    @PostMapping("account")
    @DigestLogAnnotated(AccountingConstants.QUERY_DIGEST_LOG)
    public AccountManageResult openAccount(@RequestBody OpenAccountReq openAccountReq) {

        final AccountManageResult accountManageResult = new AccountManageResult();//建立一个返回对象

        TradeTemplate.trade(accountManageResult, new TradeCallBack() {
            @Override
            public void checkParameter() {

                checkParamNotNull(openAccountReq, USER_REQUEST);

                checkParamNotNull(openAccountReq.getAccountType(), ACCOUNT_TYPE);
                checkParaNotBlank(openAccountReq.getAccountType(), ACCOUNT_TYPE);
                checkParaMatch(openAccountReq.getAccountType(), ACCOUNT_TYPE);


                checkParamNotNull(openAccountReq.getCurrency(), ACCOUNT_CURRENCY);
                checkParaNotBlank(openAccountReq.getCurrency(), ACCOUNT_CURRENCY);
                checkParaMatch(openAccountReq.getCurrency(), ACCOUNT_CURRENCY);

                if (openAccountReq.getAccountType().equals("03")) {
                    checkParamNotNull(openAccountReq.getAccountName(), ACCOUNT_NAME);
                    checkParaNotBlank(openAccountReq.getAccountName(), ACCOUNT_NAME);
                }

            }

            @Override
            @Transactional
            public void doTrade() {

                // 2、生成账号
                String accountNo = getAccountNo(openAccountReq);

                // 3、数据库插入
                InnerAccountInfoDO innerAccountInfoDO = null;
                AccountDO accountDO = buildAccount(accountNo, openAccountReq);
                if (openAccountReq.getAccountType().equals("03")) {
                    innerAccountInfoDO = buildInnerAccountInfo(accountNo, openAccountReq);
                    innerAccountInfoMapper.insert(innerAccountInfoDO);
                }
                accountMapper.insert(accountDO);


                // 4、返回开户结果
                accountManageResult.setAccountNo(accountNo);
                accountManageResult.setSuccess(true);

            }

//            @Override
//            public QueryDigestLog buildDigestLog() {
//                return new QueryDigestLog(openAccountReq);
//            }
        });

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

    /**
     * 内部户扩展表建立方法
     * 通过传入参数建立内部户扩展表实体类
     *
     * @param accountNo      账号
     * @param openAccountReq 开户请求对象
     * @return 内部户扩展表实体类
     */
    private InnerAccountInfoDO buildInnerAccountInfo(String accountNo, OpenAccountReq openAccountReq) {

        InnerAccountInfoDO innerAccountInfoDO = new InnerAccountInfoDO();
        innerAccountInfoDO.setTitleCode(openAccountReq.getTitleCode());
        innerAccountInfoDO.setRelationInstId(openAccountReq.getRelationInstId());
        innerAccountInfoDO.setRelationCode(openAccountReq.getRelationCode());
        innerAccountInfoDO.setReconInst(openAccountReq.getReconInst());
        innerAccountInfoDO.setAccountNo(accountNo);

        return innerAccountInfoDO;
    }


}
