package com.lec.proxy;

/**
 * 生产者
 */
public class Producer implements IProducer {

    @Override
    public void saleProduct(float money) {
        System.out.println("出售商品，价格：" + money);
    }

    @Override
    public void afterService(float money) {
        System.out.println("售后服务，价格：" + money);
    }

}
