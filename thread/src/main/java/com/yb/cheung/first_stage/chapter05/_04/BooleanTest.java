package com.yb.cheung.first_stage.chapter05._04;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static java.lang.Thread.currentThread;
import static java.util.concurrent.ThreadLocalRandom.current;

public class BooleanTest {

    // 定义Boolean
    private final Lock lock = new BooleanLock();

    // 使用try..finally语句块确保lock每次都能被正确释放
    public void syncMethod(){
        try {
            // 加锁
            lock.lock();
            int randomInt = current().nextInt(10);
            System.out.println(currentThread() + " get the lock." );
            TimeUnit.SECONDS.sleep(randomInt);
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            // 释放锁
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        /**
         *  多个线程通过lock方法争抢锁
         */
        /*BooleanTest blt = new BooleanTest();
        // 定义一个线程并且启动
        IntStream.range(0,10).mapToObj(i->new Thread(blt::syncMethod)).forEach(Thread::start);*/

        /**
         *  可中断被阻塞的线程
         */
        BooleanTest blt = new BooleanTest();
        new Thread(blt::syncMethod,"T1").start();
        TimeUnit.MILLISECONDS.sleep(2);
        Thread t2 = new Thread(blt::syncMethod,"T2");
        t2.start();
        TimeUnit.MILLISECONDS.sleep(10);
        t2.interrupt();
    }

}
