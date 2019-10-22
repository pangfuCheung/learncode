package com.yb.cheung.pattern.mutilproxy;

import java.util.List;

public class MultistageProxyFactory {

    public Object newInstanceProctory(Class classFile) throws Exception{
        Configuration configuration = new Configuration();
        List plugins = configuration.getPlugins();
        Object implObj = classFile.newInstance();

        for (Object object:plugins){

        }

        return null;
    }

}
