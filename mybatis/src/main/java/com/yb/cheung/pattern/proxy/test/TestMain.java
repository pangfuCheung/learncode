package com.yb.cheung.pattern.proxy.test;

import com.yb.cheung.pattern.proxy.Dao;
import com.yb.cheung.pattern.proxy.Impl;
import com.yb.cheung.pattern.proxy.ProxyFactory;

public class TestMain {

    public static void main(String[] args) {

        Dao dao = (Dao) ProxyFactory.newInstanceDao(Dao.class,new Impl());
        System.out.println(dao.getInteger());
        System.out.println(dao.getString());

    }

}
