package com.lec.jdbc.template.dao.impl;

import com.lec.ioc.domain.Account;
import com.lec.jdbc.template.dao.AccountDao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 *
 */
public class AccountDaoImpl implements AccountDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Account findAccountById(Integer id) {
        List<Account> list = jdbcTemplate.query("select * from account where id = ?",
                new BeanPropertyRowMapper(Account.class), id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public Account findAccountByName(String name) {
        List<Account> list = jdbcTemplate.query("select * from account where name = ?",
                new BeanPropertyRowMapper(Account.class), name);
        if (list.isEmpty())
            return null;
        if (list.size() > 1)
            throw new RuntimeException("结果集不唯一");

        return list.get(0);
    }

    @Override
    public void updateAccount(Account account) {
        jdbcTemplate.update("update account set name = ?,money = ? where id = ?",
                account.getName(), account.getMoney(), account.getId());
    }
}
