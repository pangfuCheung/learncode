package com.yb.cheung.ibatis.session;

import com.yb.cheung.ibatis.configuration.Configuration;
import com.yb.cheung.ibatis.proxy.ProxyFactory;

import java.lang.reflect.Proxy;

public class SqlSessionFactory {

    private Configuration configuration;

    public SqlSessionFactory(Configuration configuration){
        this.configuration = configuration;
    }

    public Object getMapper(Class classFile){
        ProxyFactory proxyFactory = new ProxyFactory(configuration,classFile);
        Class classArray[] = {classFile};
        return Proxy.newProxyInstance(classFile.getClassLoader(),classArray,proxyFactory);
    }
}
