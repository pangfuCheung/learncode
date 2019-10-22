package com.yb.cheung.first_stage.chapter07._02;

import java.util.concurrent.TimeUnit;

/**
 * JVM进程的退出是由于JVM中没有活跃的非守护线程，或者收到了系统中断信号，向JVM程序注入一个Hook线程，
 * 在JVM进程退出的时候，或者收到了系统中断信号，向JVM程序注入一个Hook线程，在JVVM进程退出的时候，
 * Hook线程会启动执行，通过Runtime可以为JVM注入多个Hook线程
 */
public class ThreadHook {

    public static void main(String[] args) {
        // 为应用程序注入钩子线程
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                try {
                    System.out.println("The Hook thread 1 is running....");
                    TimeUnit.SECONDS.sleep(1);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println(" The hook thread 1 will exit.");
            }
        });

        // 钩子线程可注册多个
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                try {
                    System.out.println("The Hook thread 2 is running....");
                    TimeUnit.SECONDS.sleep(1);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println(" The hook thread 2 will exit.");
            }
        });

        System.out.println(" The program will is stopping.");

    }

}
