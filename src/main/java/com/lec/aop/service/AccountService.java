package com.lec.aop.service;

/**
 * 账户的业务层接口
 */
public interface AccountService {

    void saveAccount();

    void updateAccount(Integer id);

    int deleteAccount();

}
