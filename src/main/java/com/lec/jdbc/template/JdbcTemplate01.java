package com.lec.jdbc.template;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * @author zhwanwan
 * @create 2019-07-22 9:10 PM
 */
public class JdbcTemplate01 {

    public static void main(String[] args) {
        //准备数据源:Spring内置
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/sampdb");
        ds.setUsername("root");
        ds.setPassword("root");

        //1.创建JdbcTemplate
        JdbcTemplate jt = new JdbcTemplate(ds);
        //2.执行操作
        jt.execute("insert into account(name,money) values ('fff',4444)");
    }
}
