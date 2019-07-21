package com.lec.aop.service.impl;

import com.lec.aop.service.AccountService;
import org.springframework.stereotype.Service;

/**
 * 账户的业务层实现类
 * --------------------------------------------------
 * Spring AOP
 * JoinPoint(连接点）:
 *  JoinPoint是指那些被拦截到的点。在spring中，这些点指的是方法，因为spring只支持方法类型的连接点。
 * Pointcut(切入点):
 *  Pointcut是我们要对哪些JoinPoint进行拦截的定义。
 *      只有被增强的方法才是切入点，因此所有的切入点都是连接点，但是并不是所有的连接点都是切入点。
 * Advice(通知/增强):
 *  Advice是指拦截到JoinPoint之后所要做的事情。
 *  通知的类型：前置通知、异常通知、最终通知和环绕通知。
 * Introduction(介入):
 *  Introduction是一种特殊的通知，在不修改类代码的前提下，Introduction可以在运行期为类动态地增加一些方法或Field。
 * Target(目标对象)：
 *  Target是代理的目标对象。
 * Weaving(织入):
 *  织入是指把增强应用到目标对象来创建新的代理对象的过程。
 *  spring采用动态代理织入，而AspectJ采用编译期织入和类装载期织入。
 * Proxy(代理):
 *  一个类被APO织入增强后，就产生一个结果代理类。
 * Aspect(切面):
 *  Aspect是切入和通知(介入)的结合。
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @Override
    public void saveAccount() {
        int i = 1/0;
        System.out.println("saveAccount");
    }

    @Override
    public void updateAccount(Integer id) {
        System.out.println("updateAccount " + id);
    }

    @Override
    public int deleteAccount() {
        System.out.println("deleteAccount");
        return 0;
    }
}
