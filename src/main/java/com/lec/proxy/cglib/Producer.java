package com.lec.proxy.cglib;

import com.lec.proxy.IProducer;

/**
 * 生产者
 */
public class Producer {

    public void saleProduct(float money) {
        System.out.println("出售商品，价格：" + money);
    }

    public void afterService(float money) {
        System.out.println("售后服务，价格：" + money);
    }

}
