package com.lec.ioc;

import com.lec.config.SpringConfiguration;
import com.lec.ioc.domain.Account;
import com.lec.ioc.service.AccountService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Junit Test
 * Spring整合Junit配置
 *  1、导入spring整合junit的jar包
 *  2、使用Junit提供的一个注解把原有的main方法替换了，换成spring提供的RunWith注解
 *      @RunWith
 *  3、告知spring的运行器，spring和ioc创建时基于xml还是基于注解的，并且说明位置
 *      @ContextConfiguration
 *          locations：指定xml文件的位置，加上classpath表示类路径
 *          classes：指定注解类所在的位置
 * 当我们使用spring 5.x版本时，要求Junit的jar包必须要是4.1.2及以上
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class)
public class AccountServiceTest {

    /*private static final ApplicationContext context;
    private static final AccountService accountService;

    static {
        //context = new ClassPathXmlApplicationContext("bean.xml");
        context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        accountService = context.getBean("accountService", AccountService.class);
    }*/

    /*private ApplicationContext context;
    private AccountService accountService;

    @Before
    public void init() {
        context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        accountService = context.getBean("accountService", AccountService.class);
    }*/

    @Autowired
    private AccountService accountService;

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
