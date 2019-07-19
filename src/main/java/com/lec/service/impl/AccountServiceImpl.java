package com.lec.service.impl;

import com.lec.dao.AccountDao;
import com.lec.dao.impl.AccountDaoImpl;
import com.lec.service.AccountService;

import java.util.Date;

/**
 * 控制反转：
 * Inversion of Control(IoC):把创建对象的权利交给框架，是框架的重要特征，
 * 并非面向对象编程的专用术语。它包括依赖注入（Dependency Injection,DI)和
 * 依赖查找（Dependency LookUp）。
 */
public class AccountServiceImpl implements AccountService {

    private AccountDao accountDao = new AccountDaoImpl(); //拥有控制权

    //private AccountDao accountDao = (AccountDao) BeanFactory.getBean("accountDao");

    public AccountServiceImpl() {
        System.out.println("AccountService对象创建了");
    }

    @Override
    public void saveAccount() {
        //accountDao.saveAccount();
        System.out.println("save account service: " + name + "," + age + "," + birthday);
    }

    public void init() {
        System.out.println("AccountService初始化");
    }

    public void destroy() {
        System.out.println("AccountService销毁");
    }

    private String name;
    private Integer age;
    private Date birthday;

    public AccountServiceImpl(String name, Integer age, Date birthday) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
