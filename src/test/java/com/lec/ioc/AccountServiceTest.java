package com.lec.ioc;

import com.lec.ioc.domain.Account;
import com.lec.ioc.service.AccountService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Junit Test
 */
public class AccountServiceTest {

    private static final ApplicationContext context;
    private static final AccountService accountService;

    static {
        context = new ClassPathXmlApplicationContext("bean.xml");
        accountService = context.getBean("accountService", AccountService.class);
    }

    @Test
    public void findAll() {
        List<Account> list = accountService.findAll();
        System.out.println(list);
    }

    @Test
    public void findOne() {
        Account account = accountService.findAccountById(2);
        System.out.println(account);
    }

    @Test
    public void save() {
        Account account = new Account();
        account.setName("ddd");
        account.setMoney(3000.0f);
        accountService.saveAccount(account);
    }

    @Test
    public void update() {
        Account account = accountService.findAccountById(1);
        account.setMoney(5000.f);
        accountService.updateAccount(account);

    }

    @Test
    public void delete() {
        accountService.deleteAccount(3);
    }
}
