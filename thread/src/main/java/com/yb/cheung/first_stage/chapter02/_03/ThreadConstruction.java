package com.yb.cheung.first_stage.chapter02._03;

public class ThreadConstruction {

    public static void main(String[] args) {

        //01
        Thread thread1 = new Thread();

        //02
        ThreadGroup group = new ThreadGroup("02group");

        Thread thread2 = new Thread(group,"t2");

        ThreadGroup mainThreadGroup =  Thread.currentThread().getThreadGroup();

        System.out.println("mian method Thread Group ..... " +mainThreadGroup.getName() );

        System.out.println(thread1.getThreadGroup().getName());

        System.out.println(thread2.getThreadGroup().getName());

    }

}
