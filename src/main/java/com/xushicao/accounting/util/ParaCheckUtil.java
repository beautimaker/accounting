/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.util;

import com.xushicao.accounting.dao.entity.AccountDO;
import com.xushicao.accounting.domain.enums.AccountCurrencyEnum;
import com.xushicao.accounting.domain.enums.AccountTypeEnum;
import com.xushicao.accounting.facade.req.TransReq;
import com.xushicao.accounting.facade.req.TransferReq;
import com.xushicao.accounting.model.enums.AccountingErrDtlEnum;
import com.xushicao.accounting.model.exception.AccountingException;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 参数校验工具类
 *
 * @author Shichao.xu
 * @version $ ParaCheckUtil, V0.1 2024/4/14 14:41 Shichao.xu Exp $
 */

public class ParaCheckUtil {

    /**
     * 请求对象
     */
    public static final String USER_REQUEST = "请求对象:request";

    /**
     * 用户名称
     */
    public static final String ACCOUNT_NAME = "账户名称:name";

    /**
     * 账户类型
     */
    public static final String ACCOUNT_TYPE = "账户类型:type";

    /**
     * 币种
     */
    public static final String ACCOUNT_CURRENCY = "币种:currency";

    /**
     * 账号
     */
    public static final String ACCOUNT_NUMBER = "账号:number";

    /**
     * 余额
     */
    public static final String ACCOUNT_AMOUNT = "金额:amount";

    /**
     * 操作员ID
     */
    public static final String ACCOUNT_OPERATOR_ID = "操作员ID";

    /**
     * 订单号
     */
    public static String ACCOUNT_ORDER_NO = "订单号:orderNo";

    /**
     * 核算机构
     */
    public static final String RECON_INST = "核算机构:reconInst";

    /**
     * 交易日期
     */
    public static final String TRANS_DATE = "交易日期:transDate";

    /**
     * 交易时间
     */
    public static final String TRANS_D_T = "交易时间:transDT";

    /**
     * 交易代码
     * DEP-存款
     * WDL-取款
     * XFR-转账
     */
    public static final String TRANS_CODE = "交易代码:transCode";

    /**
     * 交易子代码
     * 01-现金存款，现金取款，同行转账
     * 02-支票存款，支票取款，跨行转账
     * 03-电子转账存款，ATM取款，境外转账
     */
    public static final String SUB_TRANS_CODE = "交易子代码:subTransCode";

    /**
     * 外部业务时间
     */
    public static final String OUT_DATE = "外部业务时间:outDate";


    /**
     * 私有构造函数
     */
    private ParaCheckUtil() {

    }

    /**
     * 检查请求对象是否为空
     *
     * @param para     检查对象
     * @param paraName 提醒信息
     */
    public static void checkParamNotNull(Object para, String paraName) {
        if (para == null) {
            String msg = paraName + "不允许为null";
            throw new AccountingException(AccountingErrDtlEnum.REQ_PARAM_NOT_VALID, msg);
        }
    }

    public static void checkParaNotBlank(String paraValue, String paraName) {
        if (paraValue == null || paraValue.isBlank()) {
            String msg = paraName + "不允许为空";
            throw new AccountingException(AccountingErrDtlEnum.REQ_PARAM_NOT_VALID, msg);
        }

    }

    public static void checkParaMatch(String paraValue, String paraName) {
        if (paraName.equals("账户类型:type")) {
            if (AccountTypeEnum.getByCode(paraValue) == null) {
                String msg = paraName + "不在指定范围内";
                throw new AccountingException(AccountingErrDtlEnum.REQ_PARAM_NOT_VALID, msg);
            }
        } else if (paraName.equals("币种:currency")) {
            if (AccountCurrencyEnum.getByCode(paraValue) == null) {
                String msg = paraName + "不在指定范围内";
                throw new AccountingException(AccountingErrDtlEnum.REQ_PARAM_NOT_VALID, msg);
            }
        }
    }

    public static void checkTransParams(TransReq transReq) {
        checkParamNotNull(transReq.getAmount(), ACCOUNT_AMOUNT);
        checkParamNotNull(transReq.getAmount(), ACCOUNT_AMOUNT);
        checkParaNotBlank(transReq.getOperatorID(), ACCOUNT_OPERATOR_ID);
        checkParaNotBlank(transReq.getOrderNo(), ACCOUNT_ORDER_NO);
        checkParaNotBlank(transReq.getReconInst(), RECON_INST);
        checkParamNotNull(transReq.getTransDate(), TRANS_DATE);
        checkParamNotNull(transReq.getTransDT(), TRANS_D_T);
        checkParaNotBlank(transReq.getTransCode(), TRANS_CODE);
        //添加参数是否匹配
        checkParaNotBlank(transReq.getSubTransCode(), SUB_TRANS_CODE);
        //添加参数是否匹配
        checkParamNotNull(transReq.getOutDate(), OUT_DATE);
    }
}