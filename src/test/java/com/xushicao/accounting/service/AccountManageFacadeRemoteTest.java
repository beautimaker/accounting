/**
 * Shichao.com Inc
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.xushicao.accounting.service;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


/**
 * 远程开户测试类
 * 使用mockmvc实现远程开户测试
 *
 * @author Shichao.xu
 * @version $ AccountManageFacadeRemoteTest, V0.1 2024/4/11 10:06 Shichao.xu Exp $
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AccountManageFacadeRemoteTest {

    /**
     * 端口变量
     * 将实际运行的服务器端口注入到端口变量中
     */
    @LocalServerPort
    private int port;

    /**
     * Restful API的工具类
     * 用于模拟http 请求向服务器发送请求，并获取响应
     */
    private TestRestTemplate testRestTemplate = new TestRestTemplate();

    /**
     * 远程测试开户函数
     *
     * @throws Exception
     */
    
    @Test

    public void remoteTestOpenAccount() {

        String requsetBody = null; //请求体对象，用于设置请求体
        String url = "http://localhost:" + port + "/account"; //设置url地址
        HttpHeaders headers = new HttpHeaders();//请求头对象
        HttpEntity<String> request = null;//请求实体包含请求头和请求体
        ResponseEntity<String> response = null;//响应实体包含响应头和响应体


        //case1: 个人人民币账户远程正常开户
        requsetBody = "{\"accountName\":\"xushichao\",\"accountType\":\"01\",\"currency\":\"156\"}";
        headers.setContentType(MediaType.APPLICATION_JSON);
        request = new HttpEntity<>(requsetBody, headers);

        response = testRestTemplate.exchange(url, HttpMethod.POST, request, String.class);

        System.out.println(response.toString());


        //case2:公司美元账户远程正常开户
        requsetBody = "{\"accountName\":\"tailinxi\",\"accountType\":\"02\",\"currency\":\"840\"}";
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        request = new HttpEntity<>(requsetBody, headers);

        response = testRestTemplate.exchange(url, HttpMethod.POST, request, String.class);
        System.out.println(response);


        //case3:内部欧元账户远程正常开户
        requsetBody = "{\"accountName\":\"lijiangwei\",\"accountType\":\"03\",\"currency\":\"978\",\"titleCode\"" +
                ":\"资产类科目\",\"reconInst\":\"中国农业银行\",\"relationInstId\":\"PBOC\",\"relationCode\":" +
                "\"1234567891234567891234567912345678912345678912345678912345678912345678910\"}";
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        request = new HttpEntity<>(requsetBody, headers);

        response = testRestTemplate.exchange(url, HttpMethod.POST, request, String.class);
        System.out.println(response);

    }
}