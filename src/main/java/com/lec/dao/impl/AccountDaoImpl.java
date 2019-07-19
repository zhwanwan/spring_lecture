package com.lec.dao.impl;

import com.lec.dao.AccountDao;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository("accountDao1")
public class AccountDaoImpl implements AccountDao {

    @Override
    public void saveAccount() {
        System.out.println("save account dao1");
    }
}
