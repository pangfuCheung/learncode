package com.yb.cheung.pattern.proxy;

import java.lang.reflect.Proxy;

public class ProxyFactory {

    public static Object newInstanceDao(Class classFile,Impl impl){
        Agent agent = new Agent(impl);
        Class classArray[] = {classFile};
        return Proxy.newProxyInstance(classFile.getClassLoader(),classArray,agent);
    }

}
