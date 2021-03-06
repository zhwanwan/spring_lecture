package com.lec.tx.service.impl;

import com.lec.ioc.domain.Account;
import com.lec.jdbc.template.dao.AccountDao;
import com.lec.tx.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 基于注解的事务
 */
@Service("accountService")
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class AccountServiceImpl2 implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @Override
    public Account findAccountById(Integer id) {
        return accountDao.findAccountById(id);
    }

    @Override
    @Transactional(readOnly = false)
    public void transfer(String sourceName, String targetName, Float money) {

        /*try {
            //1.开启事务
            transactionManager.beginTransaction();
            //2.执行操作
            //2.1.查询转出账户
            Account source = accountDao.findAccountByName(sourceName);
            //2.2.查询转入账户
            Account target = accountDao.findAccountByName(targetName);
            //2.3.转出账户扣钱
            source.setMoney(source.getMoney() - money);
            //2.4.转出账户加钱
            target.setMoney(target.getMoney() + money);
            //2.5.更新转出账户
            accountDao.updateAccount(source);
            //2.6.更新转入账户
            accountDao.updateAccount(target);

            //故意报错
            int i = 1 / 0;

            //3.提交事务
            transactionManager.commit();
        } catch (Exception e) {
            //5.事务回滚
            transactionManager.rollback();
            throw new RuntimeException(e);
        } finally {
            //6.释放连接
            transactionManager.release();
        }*/

        //2.1.查询转出账户
        Account source = accountDao.findAccountByName(sourceName);
        //2.2.查询转入账户
        Account target = accountDao.findAccountByName(targetName);
        //2.3.转出账户扣钱
        source.setMoney(source.getMoney() - money);
        //2.4.转出账户加钱
        target.setMoney(target.getMoney() + money);
        //2.5.更新转出账户
        accountDao.updateAccount(source);

        //故意报错
//        int i = 1 / 0;

        //2.6.更新转入账户
        accountDao.updateAccount(target);


    }
}
