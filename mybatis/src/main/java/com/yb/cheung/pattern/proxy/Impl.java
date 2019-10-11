package com.yb.cheung.pattern.proxy;

public class Impl implements Dao {

    public Integer getInteger() {
        return 1;
    }

    public String getString() {
        return "string";
    }
}
