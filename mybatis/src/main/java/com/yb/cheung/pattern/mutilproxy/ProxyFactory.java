package com.yb.cheung.pattern.mutilproxy;

import java.lang.reflect.Proxy;

public class ProxyFactory {

    public static Object newInstanceProxy(){

        Class baseDaoArry[] = {BaseDao.class};

        TangLangAgent chanTangLangAgent = new TangLangAgent(new ChanImpl());

        Object chan$proxy = Proxy.newProxyInstance(BaseDao.class.getClassLoader(),baseDaoArry, chanTangLangAgent);

        HuangQueAgent huangQueAgent = new HuangQueAgent(chan$proxy);

        BaseDao chan$proxy1 = (BaseDao) Proxy.newProxyInstance(BaseDao.class.getClassLoader(),baseDaoArry,huangQueAgent);

        return chan$proxy1;
    }

}
