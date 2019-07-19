package com.lec.factory;

import com.lec.service.AccountService;
import com.lec.service.impl.AccountServiceImpl;

/**
 *
 */
public class StaticFactory {

    public static AccountService getAccountService() {
        return new AccountServiceImpl();
    }
}
