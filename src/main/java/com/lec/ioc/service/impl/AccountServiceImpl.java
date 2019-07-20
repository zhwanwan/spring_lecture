package com.lec.ioc.service.impl;

import com.lec.ioc.dao.AccountDao;
import com.lec.ioc.domain.Account;
import com.lec.ioc.service.AccountService;
import com.lec.utils.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 账户的业务层实现类
 * 事务控制应该在业务层
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    private TransactionManager transactionManager;

    public void setTransactionManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public List<Account> findAll() {
        try {
            //1.开启事务
            transactionManager.beginTransaction();
            //2.执行操作
            List<Account> list = accountDao.findAll();
            //3.提交事务
            transactionManager.commit();
            //4.返回结果
            return list;
        } catch (Exception e) {
            //5.事务回滚
            transactionManager.rollback();
            throw new RuntimeException(e);
        } finally {
            //6.释放连接
            transactionManager.release();
        }
    }

    @Override
    public Account findAccountById(Integer id) {
        return accountDao.findAccountById(id);
    }

    @Override
    public void saveAccount(Account account) {
        accountDao.saveAccount(account);
    }

    @Override
    public void updateAccount(Account account) {
        accountDao.updateAccount(account);
    }

    @Override
    public void deleteAccount(Integer id) {
        accountDao.deleteAccount(id);
    }

    @Override
    public void transfer(String sourceName, String targetName, Float money) {

        try {
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
        }


    }
}
