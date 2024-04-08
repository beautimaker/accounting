package com.xushicao.accounting.service;
import com.xushicao.accounting.facade.AccountManageFacade;
import com.xushicao.accounting.facade.req.OpenAccountReq;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AccountManageFacadeTest {

    @Autowired
    private AccountManageFacade accountManageFacade;

    @Test
    void testOpenAccount(){

        accountManageFacade.openAccount(null);
    }
}
