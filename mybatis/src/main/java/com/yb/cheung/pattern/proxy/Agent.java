package com.yb.cheung.pattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class Agent implements InvocationHandler {

    private Impl impl;

    public Agent(Impl impl){
        this.impl = impl;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Method methods[] = impl.getClass().getDeclaredMethods();
        for (int i=0;i<methods.length;i++){
            if(methods[i].getName().equals(method.getName())){
                Method m = methods[i];
                return m.invoke(impl,args);
            }
        }
        return proxy;
    }
}
