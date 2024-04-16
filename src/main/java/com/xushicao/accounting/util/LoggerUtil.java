/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.util;


import org.slf4j.Logger;

/**
 * @author Shichao.xu
 * @version $ LoggerUtil, V0.1 2024/4/14 18:11 Shichao.xu Exp $
 */

public class LoggerUtil {


    public static void warn(Logger logger, String message, Throwable throwable) {
        logger.warn(message, throwable);
    }


}