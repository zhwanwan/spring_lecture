package com.lec.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * 配置类：它的作用跟bean.xml是一样的
 * spring中的新注解：
 * Configuration
 *      作用：指定当前类是一个配置类
 *      细节：当配置类作为AnnotationConfigurationContext对象创建的参数时，该注解可以不写。
 * ComponentScan
 *      作用：用于通过注解指定spring在创建容器时要扫描的包
 *      属性：
 *          value：它和basePackage的作用是一样的，都是用于指定创建容器时要扫描的包。
 *          等价于xml配置<context:component-scan base-package="com.lec.*.impl"></context:component-scan>
 * Bean
 *      作用：用于把当前方法的返回值作为bean对象存入spring的IOC容器中
 *      属性：
 *          name：用于指定bean的id,当不写时，默认值就是当前方法的名称
 *      细节：
 *          当我们使用注解配置方法时，如果方法有参数，spring会去容器中查找有没有可用的bean对象。
 *          查找的方式跟Autowired的作用是一样的。
 * Import
 *      作用：用于导入其他的配置类
 *      属性：
 *          value：用于指定其他配置类的字节码；
 *          当我们使用Import注解之后，有Import注解的类就是父配置类，而导入的都是子配置类。
 * PropertySource
 *      作用：用于指定properties文件的位置
 *      属性：
 *          value：指定文件的名称和路径。
 *              关键字：classpath表示类路径下
 *
 */
//@Configuration
//@ComponentScan(basePackages = {"com.lec.ioc", "com.lec.config"})
@ComponentScan(basePackages = "com.lec.ioc") //没有扫描配置类的包，可通过Import导入
@Import(value = JdbcConfiguration.class)
@PropertySource("classpath:jdbcConfig.properties") //如果属性文件放在resource文件中，默认就是classpath(可省)
public class SpringConfiguration {



}
