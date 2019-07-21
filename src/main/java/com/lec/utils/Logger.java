package com.lec.utils;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 记录日志的工具类
 */
public class Logger {

    /**
     * 打印日志，计划让其在切入点方法执行之前执行
     */
    public void beforePrintLog() {
        System.out.println("beforePrintLog");
    }

    public void afterReturningPrintLog(){
        System.out.println("afterReturningPrintLog");
    }

    public void afterThrowingPrintLog(){
        System.out.println("afterThrowingPrintLog");
    }

    public void afterPrintLog(){
        System.out.println("afterPrintLog");
    }

    /**
     * 环绕通知：
     * 问题：
     *  当我们配置了环绕通知之后，切入点方法没有执行，而通知方法执行了。
     * 分析：
     *  通过对比动态代理中的环绕通知代码，发现动态代理的环绕通知有明确的切入点方法调用。
     * 解决：
     *  Spring提供了一个接口：ProceedJoinPoint。该接口有一个processed()方法，此方法就相当于明确调用了切入点方法。
     *  该接口可以作为环绕通知的方法参数，在程序执行时，spring会提供该接口的实现类。
     */
    public Object aroundPrintLog(ProceedingJoinPoint pjp){
        try {
            Object rtValue = null;
            System.out.println("around--->before");
            Object[] args = pjp.getArgs(); //得到方法执行所需的参数
            rtValue = pjp.proceed(args);//明确调用业务层方法(切入点方法)
            System.out.println("around--->return");
            return rtValue;
        } catch (Throwable throwable) {
            System.out.println("around--->afterThrowing");
            throw new RuntimeException(throwable);
        } finally {
            System.out.println("around--->after");
        }
    }

}
