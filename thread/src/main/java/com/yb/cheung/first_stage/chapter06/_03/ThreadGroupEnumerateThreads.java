package com.yb.cheung.first_stage.chapter06._03;

import java.util.concurrent.TimeUnit;

public class ThreadGroupEnumerateThreads {

    public static void main(String[] args) {
        // 创建一个ThreadGroup
        ThreadGroup myGroup = new ThreadGroup("MyGroup");
        // 创建线程传入threadgroup
        Thread thread = new Thread(myGroup,()->{
            while (true){
                try {
                    TimeUnit.SECONDS.sleep(1);
                }catch (InterruptedException e){

                }
            }
        },"MyThread");
        thread.start();
        try {
            TimeUnit.MILLISECONDS.sleep(2);
        }catch (InterruptedException e){
        }
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();

        Thread[] list = new Thread[mainGroup.activeCount()];
        int recurseSize = mainGroup.enumerate(list);
        System.out.println(recurseSize);

        recurseSize = mainGroup.enumerate(list,false);
        System.out.println(recurseSize);

    }

}
