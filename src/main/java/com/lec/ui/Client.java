package com.lec.ui;

import com.lec.dao.AccountDao;
import com.lec.service.AccountService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 获取Spring的IOC容器，并根据id获取对象
 * <p>
 * ApplicationContext的三个常用实现类：
 * ClassPathXmlApplicationContext：加载类路径下的配置文件，要求配置文件必须在类路径下，否则加载不了。（重用）
 * FileSystemXmlApplicationContext：加载任意路径下的配置文件（必须要有访问权限）
 * AnnotationConfigApplicationContext：读取注解创建容器
 * <p>
 * 核心容器的两个接口引发出的问题：
 * ApplicationContext：单例对象适用--实际使用的
 * 它在构建核心容器时，创建对象采取的策略是立即加载的方式。也就是说，只要一读取配置文件马上就创建文件中配置的对象。
 * BeanFactory：多例对象适用
 * 它在构建核心容器时，创建对象采取的策略是延迟加载的方式。也就是说，什么时候根据id获取对象，什么时候才真正创建对象。
 */
public class Client {


    public static void main(String[] args) {

        //AccountService as = new AccountServiceImpl();
        /*for (int i = 0; i < 5; i++) {

            AccountService as = (AccountService) BeanFactory.getBean("accountService");
            System.out.println(as);
        }*/
        /*AccountService as = (AccountService) BeanFactory.getBean("accountService");
        as.saveAccount();*/

        //**********通过Spring获取bean对象***********


        //-----------------ApplicationContext方式----------------
        //1.获取核心容器对象
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
//        ApplicationContext context = new FileSystemXmlApplicationContext("E:\\2019\\spring\\src\\main\\resources\\bean.xml");
        //2.根据id获取bean对象
        AccountService as = (AccountService) context.getBean("accountService");
        //AccountService as2 = (AccountService) context.getBean("accountService");
        //System.out.println(as == as2); // true：单例
        //AccountService actSvcIns = (AccountService) context.getBean("actSvcIns");
        //AccountDao ad = context.getBean("accountDaoImpl", AccountDao.class);
        //System.out.println(ad);
        as.saveAccount();
        //System.out.println(as);
        //System.out.println(actSvcIns);

        //AccountService actSvcStatic = (AccountService) context.getBean("acctSvcStatic");
        //System.out.println(actSvcStatic);

        //手动关闭容器
        context.close();

        //---------------BeanFactory方式-----------------
        /*Resource resource = new ClassPathResource("bean.xml");
        BeanFactory factory = new XmlBeanFactory(resource);
        AccountService accountService = (AccountService) factory.getBean("accountService");
        AccountDao accountDao = (AccountDao) factory.getBean("accountDao");
        System.out.println(accountService);
        System.out.println(accountDao);*/


    }
}
