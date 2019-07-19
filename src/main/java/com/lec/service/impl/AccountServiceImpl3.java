package com.lec.service.impl;

import com.lec.dao.AccountDao;
import com.lec.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * Spring注解注入
 * <p>
 *1、创建对象的注解
 * @Component： 作用：用于把当前类对象存入spring容器
 * 属性：
 * value：用于指定bean的id,默认是当前类名且首字母小写
 * Controller：一般用在表现层
 * <p>
 * Service：一般用在业务层
 * <p>
 * Repository：一般用于持久层
 * <p>
 * 以上三个是spring为我们提供明确的三层使用的注解
 *
 * 2、用于注入数据的注解
 *   他们的作用就和在xml配置文件中的bean标签中写一个<property>标签的作用是一样的
 *
 *   Autowired：
 *      作用：自动按照类型注入，只要容器中有唯一的bean对象类型和要注入的变量类型匹配，就可以注入成功。
 *          如果IOC容器中没有任何bean的类型和要注入的类型一样，则报错；
 *          如果IOC容器中有多个类型匹配时，
 *      出现位置：
 *          可以是变量上，也可以是方法上
 *      细节：
 *          使用注解注入时，set方法就可以不用了
 *
 *   Qualifier：
 *      作用：在按照类中注入的基础之上再按照名称注入，它在给类成员注入时不能单独使用，但是在给方法参数注入时可以
 *      属性：
 *          value：用于指定注入bean的id
 *   Resource：
 *      作用：直接按照bean的id注入，可以独立使用
 *      属性：
 *          name：用于指定bean的id
 *   以上三个注解都只能注入其他bean类型的数据，而基本类型和String类型无法使用上述注解实现~~(使用Value注解)；
 *   另外，集合类型的注入只能通过XML来实现。
 *
 *  Value：
 *      作用：用于注入基本类型和String类型的数据
 *      属性：
 *          value：用于指定数据的值，它可以使用spring中的SpEL(spring的el表达式）
 *              SpEL的写法：${表达式}
 *
 * 3、用于改变作用范围的注解
 *      它们的作用就和在bean标签中使用scope属性实现的功能是一样的。
 *   Scope：
 *      作用：用于指定bean的作用范围
 *      属性：
 *          value：指定范围的取值：singleton(默认)、prototype
 *
 * 4、生命周期相关的注解
 *      它们的作用就和在bean标签中使用init-method和destroy-method的作用一样
 *   PreDestroy
 *      作用：用于指定销毁方法
 *   PostConstruct
 *      作用：用于指定初始化方法
 *
 */

@Component("accountService")
@Scope("singleton") //单例--默认行为
public class AccountServiceImpl3 implements AccountService {

    /*@Autowired
    @Qualifier("accountDao2")*/
    @Resource(name = "accountDao1")
    private AccountDao accountDao;

    public AccountServiceImpl3() {
        System.out.println("AccountServiceImpl3 default constructor");
    }

    @Override
    public void saveAccount() {
        accountDao.saveAccount();
        System.out.println("AccountServiceImpl3 saveAccount");
    }

    @PostConstruct
    public void init() {
        System.out.println("AccountService初始化");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("AccountService销毁");
    }
}
