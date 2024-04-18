/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.util;

import com.xushicao.accounting.domain.enums.AccountCurrencyEnum;
import com.xushicao.accounting.domain.enums.AccountTypeEnum;
import com.xushicao.accounting.model.enums.AccountingErrDtlEnum;
import com.xushicao.accounting.model.exception.AccountingException;
import org.springframework.util.StringUtils;

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
     * 私有构造函数
     */
    private ParaCheckUtil() {

    }

    /**
     * 检查请求对象是否为空
     *
     * @param para
     * @param paraName
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
}