package com.lec.factory;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 一个创建Bean对象的工厂
 * <p>
 * Bean：在计算机应用中，有可重用组件的含义。
 * JavaBean: 用Java语言编写的可重用组件。
 * javabean --> 实体类
 * <p>
 * 1.需要一个配置文件来配置service和dao
 * 配置的内容：唯一标识=全限定类名(key=value)
 * 2.通过读取配置文件的内容，通过发射创建对象
 * <p>
 * 配置文件可以是xml也可以是properties
 */
@Deprecated
public class BeanFactory {

    private static Properties props;

    //定义一个map,用于存放要创建的对象--容器
    private static Map<String, Object> beans;

    static {
        try {
            //1.实例化对象
            props = new Properties();
            //2.获取properties文件流对象:注意此处使用类加载器获取流对象，避免路径问题
            InputStream in = BeanFactory.class.getClassLoader().getResourceAsStream("bean.properties");
            props.load(in);
            //实例化容器
            beans = new HashMap<>();
            //取出配置文件中所有的key
            Enumeration<Object> keys = props.keys();
            //遍历keys
            while (keys.hasMoreElements()) {
                String key = keys.nextElement().toString();
                //根据key获取value
                String beanPath = props.getProperty(key);
                //反射创建对象
                Object obj = Class.forName(beanPath).newInstance();
                //存入容器
                beans.put(key, obj);
            }
        } catch (Exception e) {
            throw new ExceptionInInitializerError("初始化properties失败");
        }
    }

    /**
     * 根据bean的名称获取bean对象
     *
     * @param beanName
     * @return
     */
    public static Object getBean(String beanName) {

        /*Object bean = null;
        try {
            String beanPath = props.getProperty(beanName);
            bean = Class.forName(beanPath).newInstance(); //每次都会调用默认构造函数创建对象
        } catch (InstantiationException | ClassNotFoundException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return bean;*/

        return beans.get(beanName);

    }

}
