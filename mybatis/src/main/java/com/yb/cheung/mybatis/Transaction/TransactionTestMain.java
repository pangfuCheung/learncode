package com.yb.cheung.mybatis.Transaction;

import com.alibaba.fastjson.JSON;
import com.yb.cheung.ibatis.bean.Goods;
import com.yb.cheung.ibatis.dao.DeptDao;
import com.yb.cheung.ibatis.dao.GoodsDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class TransactionTestMain {

    private SqlSessionFactory sqlSessionFactory;

    private SqlSession sqlSession;

    private SqlSession sqlSession2;

    @Before
    public void before() throws Exception{
        InputStream in = Resources.getResourceAsStream("myBatis-config.xml");
        SqlSessionFactoryBuilder factoryBuilder = new SqlSessionFactoryBuilder();
        Reader reader = new InputStreamReader(in);
        sqlSessionFactory = factoryBuilder.build(reader);
    }


    @Test
    public void test(){

        /**
         * 【更新、删除、提交】：事务的提交
         *     sqlSession.commit();
         *     该方法实际上调用的是DefultSqlSession中的commit()方法
         *
         *     public void commit() {
         *         //默认为false
         *         this.commit(false);
         *     }
         *
         *     public void commit(boolean force) {
         *         try {
         *             this.executor.commit(this.isCommitOrRollbackRequired(force));
         *             this.dirty = false;
         *         } catch (Exception var6) {
         *             throw ExceptionFactory.wrapException("Error committing transaction.  Cause: " + var6, var6);
         *         } finally {
         *             ErrorContext.instance().reset();
         *         }
         *     }
         *
         *     private boolean isCommitOrRollbackRequired(boolean force) {
         *         return !this.autoCommit && this.dirty || force;
         *     }
         *
         *     sqlSession.close();
         *     DefultSqlSession中的close()方法
         *     this.executor.close(this.isCommitOrRollbackRequired(false));
         *     private boolean isCommitOrRollbackRequired(boolean force) {
         *         return !this.autoCommit && this.dirty || force;
         *     }
         *
         *     close方法实际调用的是BaseExecutor中的close()方法
         *
         *      public void close(boolean forceRollback) {
         *         try {
         *             try {
         *                 this.rollback(forceRollback);
         *             } finally {
         *                 if (this.transaction != null) {
         *                     this.transaction.close();
         *                 }
         *             }
         *         } catch (SQLException var11) {
         *             log.warn("Unexpected exception on closing transaction.  Cause: " + var11);
         *         } finally {
         *             this.transaction = null;
         *             this.deferredLoads = null;
         *             this.localCache = null;
         *             this.localOutputParameterCache = null;
         *             this.closed = true;
         *         }
         *     }
         *
         *      public void rollback(boolean required) throws SQLException {
         *         if (!this.closed) {
         *             try {
         *                 //关闭一级缓存
         *                 this.clearLocalCache();
         *                 //关闭二级缓存
         *                 this.flushStatements(true);
         *             } finally {
         *                 if (required) {
         *                     this.transaction.rollback();
         *                 }
         *             }
         *         }
         *     }
         *
         *     总结：每次MyBatis做DML语句的时候，都会把this.dirty设置为true
         */
        /*sqlSession = sqlSessionFactory.openSession();
        DeptDao dao = sqlSession.getMapper(DeptDao.class);
        System.out.println(JSON.toJSONString(dao.findById(10)));
        sqlSession.close();*/


        /**
         * 乐观锁的实现
         */

        sqlSession = sqlSessionFactory.openSession();
        GoodsDao dao = sqlSession.getMapper(GoodsDao.class);
        sqlSession2 = sqlSessionFactory.openSession();
        GoodsDao dao2 = sqlSession2.getMapper(GoodsDao.class);

        //1.查询编号为1的商品
        Goods goods1 = dao.findById(10);
        Goods goods2 = dao2.findById(10);

        //2.通过会话session将商品1下架
        goods1.setStatus(2);
        dao.updateGoods(goods1);
        sqlSession.commit();

        //3.通过会话（线程）session2修改商品的名称
        goods2.setName("11");
        dao2.updateGoods(goods2);
        sqlSession2.commit();;

    }


}
