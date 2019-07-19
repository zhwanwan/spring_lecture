package com.lec.dao.impl;

import com.lec.dao.AccountDao;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository("accountDao2")
public class AccountDaoImpl2 implements AccountDao {

    @Override
    public void saveAccount() {
        System.out.println("save account dao2");
    }
}
