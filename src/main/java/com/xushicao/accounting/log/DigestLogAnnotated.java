package com.xushicao.accounting.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 日志注解
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface DigestLogAnnotated {
    /**
     * 注解值
     *
     * @return
     */
    String value();
}
