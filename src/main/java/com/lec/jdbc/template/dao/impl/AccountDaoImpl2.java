package com.lec.jdbc.template.dao.impl;

import com.lec.ioc.domain.Account;
import com.lec.jdbc.template.dao.AccountDao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.List;

/**
 * Dao实现类继承spring提供的JdbcDaoSupport
 */
public class AccountDaoImpl2 extends JdbcDaoSupport implements AccountDao {

    @Override
    public Account findAccountById(Integer id) {
        List<Account> list = getJdbcTemplate().query("select * from account where id = ?",
                new BeanPropertyRowMapper(Account.class), id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public Account findAccountByName(String name) {
        List<Account> list = getJdbcTemplate().query("select * from account where name = ?",
                new BeanPropertyRowMapper(Account.class), name);
        if (list.isEmpty())
            return null;
        if (list.size() > 1)
            throw new RuntimeException("结果集不唯一");

        return list.get(0);
    }

    @Override
    public void updateAccount(Account account) {
        getJdbcTemplate().update("update account set name = ?,money = ? where id = ?",
                account.getName(), account.getMoney(), account.getId());
    }
}
