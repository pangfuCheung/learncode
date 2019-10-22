package com.yb.cheung.ibatis.bean;

import java.io.Serializable;

public class Goods implements Serializable {

    private Integer id;

    private String name;

    private Integer status;

    private Integer version;

    public Goods(Integer id, String name, Integer status, Integer version) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.version = version;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
