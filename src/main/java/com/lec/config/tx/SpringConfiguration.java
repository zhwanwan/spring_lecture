package com.lec.config.tx;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * spring配置类，相当于bean.xml
 */
@Configuration
@ComponentScan({"com.lec.tx", "com.lec.jdbc.template.dao"})
@Import({JdbcConfig.class, TransactionConfig.class})
@PropertySource("classpath:jdbcConfig.properties")
@EnableTransactionManagement //开始事务支持
public class SpringConfiguration {

}
