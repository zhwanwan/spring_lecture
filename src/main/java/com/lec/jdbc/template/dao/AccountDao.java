package com.lec.jdbc.template.dao;

import com.lec.ioc.domain.Account;

/**
 * @author zhwanwan
 * @create 2019-07-22 10:29 PM
 */
public interface AccountDao {
    Account findAccountById(Integer id);
    Account findAccountByName(String name);
    void updateAccount(Account account);
}
