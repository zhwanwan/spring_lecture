package com.lec.jdbc.template;

import com.lec.ioc.domain.Account;
import com.lec.jdbc.template.dao.AccountDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * AccountDao使用
 */
public class JdbcTemplate04 {

    public static void main(String[] args) {
        //1.获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:bean-jt.xml");
        //2.获取对象
        AccountDao accountDao = ac.getBean("accountDao",AccountDao.class);
        //3.执行操作
        Account account = accountDao.findAccountByName("Hui");
        System.out.println(account);
    }
}
