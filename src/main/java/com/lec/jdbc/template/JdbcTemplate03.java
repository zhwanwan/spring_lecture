package com.lec.jdbc.template;

import com.lec.ioc.domain.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * JdbcTemplate--CRUD
 */
public class JdbcTemplate03 {

    public static void main(String[] args) {
        //1.获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:bean-jt.xml");
        //2.获取对象
        JdbcTemplate jt = ac.getBean("jdbcTemplate", JdbcTemplate.class);
        //3.执行操作
        //jt.execute("insert into account(name,money) values ('Allen',8888888)");

        //保存
        //jt.update("insert into account(name,money) values (?,?)", "Tom", 3534.34f);
        //更新
        //jt.update("update account set name = ?,money = ? where id = ?", "Jack", 555f, 6);
        //删除
        //jt.update("delete from account where id = ?", 5);
        //查询所有
        /*List<Account> accounts = jt.query("select * from account where money > ?",
                (rs, i) -> {
                    Account account = new Account();
                    account.setId(rs.getInt("id"));
                    account.setName(rs.getString("name"));
                    account.setMoney(rs.getFloat("money"));
                    return account;
                },
                3000);*/
        List<Account> accounts = jt.query("select * from account where money > ?",
                new BeanPropertyRowMapper<>(Account.class),
                3000);
        accounts.forEach(System.out::println);
        //查询单个
        List<Account> list = jt.query("select * from account where id = ?",
                new BeanPropertyRowMapper<>(Account.class),
                8);
        System.out.println(list.isEmpty()?"无数据":list.get(0));
        //查询返回一列（使用聚合函数，但不加group子句)
        Integer count = jt.queryForObject("select count(*) from account where money > ?", Integer.class, 3000f);
        System.out.println(count);

    }
}
