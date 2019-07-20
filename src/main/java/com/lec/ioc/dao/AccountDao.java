package com.lec.ioc.dao;

import com.lec.ioc.domain.Account;

import java.util.List;

/**
 * 账户的持久层
 */
public interface AccountDao {

    List<Account> findAll();

    Account findAccountById(Integer id);

    void saveAccount(Account account);

    void updateAccount(Account account);

    void deleteAccount(Integer id);
}
