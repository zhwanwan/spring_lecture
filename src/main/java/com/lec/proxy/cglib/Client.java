package com.lec.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.springframework.cglib.proxy.InvocationHandler;

import java.lang.reflect.Method;

/**
 * 动态代理--基于子类的动态代理
 */
public class Client {

    public static void main(String[] args) {

        Producer producer = new Producer();

        /**
         * 动态代理：
         *  特点：字节码随用随创建，随用随加载
         *  作用：不修改源码的基础上对方法增强
         *  分类：
         *      1、基于接口的动态代理
         *      2、基于子类的动态代理
         *  基于接口的动态代理：
         *      涉及的类：Enhancer
         *      提供者：第三方cglib
         *  如何创建代理对象：
         *      使用Enhancer类中的create方法
         *  创建代理对象的要求：
         *      被代理类不能是最终类
         *  create方法的参数：
         *      Class：字节码
         *          它是用于指定被代理对象的字节码。
         *      Callback：用于提供增强的代码
         *          它是让我写如何代理，一般都是写一个该接口的实现类，通常情况下都是匿名内部类，但不是必须的。
         *          一般写的都是该接口的子接口实现类：MethodInterceptor
         *
         */

        /**
         * 通过第三方的cglib包实现代理，pom文件需要引入cglib
         *         <dependency>
         *             <groupId>cglib</groupId>
         *             <artifactId>cglib</artifactId>
         *             <version>2.1_3</version>
         *         </dependency>
         */
        Producer cglibProducer = (Producer) Enhancer.create(producer.getClass(), new MethodInterceptor() {
            /**
             * 执行被代理对象的任何方法都会经过该方法
             * @param proxy
             * @param method
             * @param objects
             *      以上三个参数和基于接口的动态代理中invoke方法的参数是一样的
             * @param methodProxy 当前执行方法的代理对象
             * @return 和被代理对象方法有相同的返回值
             * @throws Throwable
             */
            @Override
            public Object intercept(Object proxy, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                //提供增强的代码
                Object returnValue = null;
                //1.获取方法执行的参数
                Float money = (Float) objects[0];
                //2.判断当前方法是不是销售
                if("saleProduct".equals(method.getName())){
                    returnValue = method.invoke(producer,money * 0.8f);
                }
                return returnValue;
            }
        });
        cglibProducer.saleProduct(10000f);

        /**
         * 通过spring自带的cglib包来实现，无需导入cglib包
         */
        Producer cglibProducerSpring = (Producer) org.springframework.cglib.proxy.Enhancer.create(producer.getClass(),
                (InvocationHandler) (o, method, objects) -> {
            //提供增强的代码
            Object returnValue = null;
            //1.获取方法执行的参数
            Float money = (Float) objects[0];
            //2.判断当前方法是不是销售
            if("saleProduct".equals(method.getName())){
                returnValue = method.invoke(producer,money * 0.8f);
            }
            return returnValue;
        });

        cglibProducerSpring.saleProduct(8000.f);


    }
}
