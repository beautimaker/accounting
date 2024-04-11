/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


/**
 * 远程开户测试类
 * 使用mcckmvc实现远程开户测试
 *
 * @author Shichao.xu
 * @version $ AccountManageFacadeRemoteTest, V0.1 2024/4/11 10:06 Shichao.xu Exp $
 */
@SpringBootTest
@WebAppConfiguration
@RunWith(SpringRunner.class)
public class AccountManageFacadeRemoteTest {
    /**
     * 虚拟mvc对象
     * 模拟http发送请求
     */
    private MockMvc mockMvc;
    /**
     *
     */
    @Autowired
    private WebApplicationContext webApplicationContext;


    /**
     * web应用配置接口对象
     * 实现web应用的相应配置
     */

    /**
     * mockMvc启动函数
     * 在启动测试之前构建mockMvc对象
     */
    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    /**
     * 远程测试开户函数
     * 使用mockMvc实现远程开户测试
     *
     * @throws Exception
     */
    @Test
    public void remoteTestOpenAccount() throws Exception {

        String requsetBody = null; //请求体对象，用于设置请求体


        //case1: 个人人民币账户远程正常开户
        requsetBody = "{\"accountName\":\"xushichao\",\"accountType\":\"01\",\"currency\":\"156\"}";

        mockMvc.perform(post("/account")
                        .contentType(MediaType.APPLICATION_JSON).content(requsetBody)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print());


        //case2:公司美元账户远程正常开户
        requsetBody = "{\"accountName\":\"tailinxi\",\"accountType\":\"02\",\"currency\":\"840\"}";

        mockMvc.perform(post("/account")
                        .contentType(MediaType.APPLICATION_JSON).content(requsetBody)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print());


        //case3:内部欧元账户原远程正常开户
        requsetBody = "{\"accountName\":\"lijiangwei\",\"accountType\":\"03\",\"currency\":\"978\"}";

        mockMvc.perform(post("/account")
                        .contentType(MediaType.APPLICATION_JSON).content(requsetBody)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print());

    }
}