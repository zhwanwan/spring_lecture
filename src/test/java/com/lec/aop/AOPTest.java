package com.lec.aop;

import com.lec.aop.service.AccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zhwanwan
 * @create 2019-07-21 8:48 PM
 */
public class AOPTest {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:bean-aop.xml");
        AccountService accountService = (AccountService) context.getBean("accountService");
        accountService.saveAccount();
    }
}
