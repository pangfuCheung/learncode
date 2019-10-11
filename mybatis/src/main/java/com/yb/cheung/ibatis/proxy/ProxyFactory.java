package com.yb.cheung.ibatis.proxy;

import com.yb.cheung.ibatis.configuration.Configuration;
import com.yb.cheung.ibatis.statement.StatementHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyFactory implements InvocationHandler {

    private Configuration configuration;

    private Class classFile;

    public ProxyFactory(Configuration configuration,Class classFile){
        this.configuration = configuration;
        this.classFile = classFile;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String classFullName = classFile.getName();
        int size = classFullName.lastIndexOf(".");
        String methodName = classFullName.substring(size + 1) +"."+ method.getName();
        StatementHandler statementHandler = new StatementHandler(configuration,methodName);
        return statementHandler.execute();
    }
}
