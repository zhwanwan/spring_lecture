package com.lec.ioc.service;

import com.lec.ioc.domain.Account;

import java.util.List;

/**
 * 账户的业务层接口
 */
public interface AccountService {

    List<Account> findAll();

    Account findAccountById(Integer id);

    void saveAccount(Account account);

    void updateAccount(Account account);

    void deleteAccount(Integer id);

}
