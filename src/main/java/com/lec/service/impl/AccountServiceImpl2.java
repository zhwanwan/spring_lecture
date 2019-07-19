package com.lec.service.impl;

import com.lec.service.AccountService;

import java.util.*;

/**
 * 控制反转：
 * Inversion of Control(IoC):把创建对象的权利交给框架，是框架的重要特征，
 * 并非面向对象编程的专用术语。它包括依赖注入（Dependency Injection,DI)和
 * 依赖查找（Dependency LookUp）。
 */
public class AccountServiceImpl2 implements AccountService {

    private String[] myStrs;
    private List<String> myList;
    private Set<String> mySet;
    private Map<String, String> myMap;
    private Properties myPros;

    public void setMyStrs(String[] myStrs) {
        this.myStrs = myStrs;
    }

    public void setMyList(List<String> myList) {
        this.myList = myList;
    }

    public void setMySet(Set<String> mySet) {
        this.mySet = mySet;
    }

    public void setMyMap(Map<String, String> map) {
        this.myMap = map;
    }

    public void setMyPros(Properties myPros) {
        this.myPros = myPros;
    }

    @Override
    public void saveAccount() {
        System.out.println(Arrays.toString(myStrs));
        System.out.println(myList);
        System.out.println(mySet);
        System.out.println(myMap);
        System.out.println(myPros);

    }
}
