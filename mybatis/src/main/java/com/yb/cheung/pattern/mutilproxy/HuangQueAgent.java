package com.yb.cheung.pattern.mutilproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class HuangQueAgent implements InvocationHandler {

    private Object chan$Proxy;

    public HuangQueAgent(Object chan$Proxy) {
        this.chan$Proxy = chan$Proxy;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object chan$proxy1 = method.invoke(chan$Proxy,args);
        eat();
        return chan$proxy1;
    }

    public void eat(){
        System.out.println("黄雀吃螳螂。。。。。");
    }

}
