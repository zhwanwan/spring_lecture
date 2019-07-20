package com.lec.ioc;

import com.lec.config.SpringConfiguration;
import com.lec.ioc.domain.Account;
import com.lec.ioc.service.AccountService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * Junit Test
 */
public class AccountServiceTest {

    /*private static final ApplicationContext context;
    private static final AccountService accountService;

    static {
        //context = new ClassPathXmlApplicationContext("bean.xml");
        context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        accountService = context.getBean("accountService", AccountService.class);
    }*/

    private ApplicationContext context;
    private AccountService accountService;

    @Before
    public void init() {
        context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        accountService = context.getBean("accountService", AccountService.class);
    }

    @Test
    public void findAll() {
        List<Account> list = accountService.findAll();
        list.forEach(System.out::println);
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
