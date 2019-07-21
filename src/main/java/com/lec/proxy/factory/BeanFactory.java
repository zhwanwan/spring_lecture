package com.lec.proxy.factory;

import com.lec.ioc.service.AccountService;
import com.lec.utils.TransactionManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 用于创建Service的代理对象工厂
 */
public class BeanFactory {

    private AccountService accountService;

    public final void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    private TransactionManager transactionManager;

    public void setTransactionManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public AccountService getAccountService() {

        AccountService proxyInstance = (AccountService) Proxy.newProxyInstance(accountService.getClass().getClassLoader(),
                accountService.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    Object rtValue = null;
                    try {
                        //1.开启事务
                        transactionManager.beginTransaction();
                        //2.执行操作
                        rtValue = method.invoke(accountService, args);
                        //3.提交事务
                        transactionManager.commit();
                        //4.返回结果
                        return rtValue;
                    } catch (Exception e) {
                        //5.事务回滚
                        transactionManager.rollback();
                        throw new RuntimeException(e);
                    } finally {
                        //6.释放连接
                        transactionManager.release();
                    }
                }
        );
        return proxyInstance;
    }
}
