package com.lec.tx.service;

import com.lec.ioc.domain.Account;

/**
 *
 */
public interface AccountService {

    Account findAccountById(Integer id);

    void transfer(String sourceName, String targetName, Float money);
}
