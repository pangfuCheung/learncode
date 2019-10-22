package com.yb.cheung.first_stage.chapter05._04;

import java.sql.Time;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static java.lang.System.currentTimeMillis;
import static java.lang.Thread.currentThread;

/**
 * 此类是模仿synchronized关键字在Java中的实现
 */
public class BooleanLock implements Lock {

    //当前拥有锁的线程
    private Thread currentThread;

    //一个boolean开关，false代表当前该锁没有任何线程获得或者已经被释放
    //true代表该锁已经被某个线程获得，该线程就是currentThread
    private boolean locked = false;

    //用来存储哪些线程在获取当前线程时进入了阻塞状态
    private final List<Thread> blockedList = new ArrayList<>();

    @Override
    public void lock() throws InterruptedException {
        synchronized (this){    //使用同步代码块的方式进行同步
            while (locked){     //如果当前锁已经被某个线程获取，则将当前线程进行阻塞，让当前线程释放this monitor的所有权
                blockedList.add(currentThread());
                this.wait();
            }
            //如果当前线程没有被其它线程获取，则线程尝试从阻塞队列中删除自己（注意：如果当前线程从未进入过阻塞队列，删除方法不回有
            // 任何影响；如果当前线程是从wait set中被唤醒的，则需要从阻塞队列中将自己删除）
            blockedList.remove(currentThread());
            this.locked = true;     //locked开关被指定为true
            this.currentThread = currentThread();       //记录获取锁的线程
        }
    }

    @Override
    public void lock(long mills) throws InterruptedException,TimeoutException {
        synchronized (this){
            if (mills <= 0){        //如果mills不合法，则默认调用lock()方法
                this.lock();
            }else{
                long remainingMills = mills;
                long endMills = currentTimeMillis() + remainingMills;
                while (locked){
                    /**
                     * 情况1
                     */
                    /*// 如果remaining小于0，则意味着当前线程被其它线程唤醒或者在指定的wait时间到了之后还没有获得锁，
                    // 这种情况下会抛出超时时间
                    if (remainingMills <= 0)
                        throw new TimeoutException("can not get the lock during " + mills);
                    if (!blockedList.contains(currentThread()))
                        blockedList.add(currentThread());
                    this.wait(remainingMills);
                    // 重新计算remainingMills时间
                    remainingMills = endMills = currentTimeMillis();*/

                    /**
                     * 情况2
                     */
                    // 暂存当前线程
                    final Thread tempThread = currentThread();
                    try {
                        if (!blockedList.contains(tempThread))
                            blockedList.add(tempThread);
                        this.wait();
                    }catch (InterruptedException e){
                        // 如果当前线程在wait时被中断，则从blockedList中将其删除，避免内存泄漏
                        blockedList.remove(tempThread);
                        // 继续抛出中断异常
                        throw e;
                    }
                }
                // 获得该锁，并且从block列表中删除当前线程，将locked的状态修改为true并且指定获得锁的线程就是当前线程
                blockedList.remove(currentThread());
                this.locked = true;
                this.currentThread = currentThread();
            }
        }
    }

    @Override
    public void unlock() {
        synchronized (this){
            // 判断当前线程是否为获取锁的那个线程，只有加了锁的线程才有资格进行解锁
            if (currentThread == currentThread()){
                // 将锁的locked状态修改为false
                this.locked = false;
                Optional.of(currentThread().getName() + " release the lock monitor.").ifPresent(System.out::println);
                // 通知其它在wait set中的线程，你们可以再次尝试抢锁了，这里使用notify和notifyAll都可以
                this.notifyAll();
            }
        }
    }

    @Override
    public List<Thread> getBlockedThreads() {
        return Collections.unmodifiableList(blockedList);
    }

}
