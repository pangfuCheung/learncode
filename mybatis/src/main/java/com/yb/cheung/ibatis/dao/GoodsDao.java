package com.yb.cheung.ibatis.dao;

import com.yb.cheung.ibatis.bean.Goods;

public interface GoodsDao {

    Goods findById(Integer id);

    int updateGoods(Goods goods);

}
