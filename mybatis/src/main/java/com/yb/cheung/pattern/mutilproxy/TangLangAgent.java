package com.yb.cheung.pattern.mutilproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TangLangAgent implements InvocationHandler {

    private BaseDao clazz;

    public TangLangAgent(BaseDao clazz) {
        this.clazz = clazz;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object chan = method.invoke(clazz,args);
        eat();
        return chan;
    }

    public void eat(){
        System.out.println("螳螂吃蝉。。。。。");
    }
}
