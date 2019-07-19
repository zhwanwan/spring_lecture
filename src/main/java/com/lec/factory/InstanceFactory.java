package com.lec.factory;

import com.lec.service.AccountService;
import com.lec.service.impl.AccountServiceImpl;

/**
 *
 */
public class InstanceFactory {

    public AccountService getAccountService() {
        return new AccountServiceImpl();
    }

}
