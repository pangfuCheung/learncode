package com.yb.cheung.ibatis.dao;

import com.yb.cheung.ibatis.bean.Dept;

import java.util.List;

public interface DeptDao {

    List findAll();

    Dept findById(Integer id);

    int deptAdd(Dept dept);

    int delById(Integer id);

}
