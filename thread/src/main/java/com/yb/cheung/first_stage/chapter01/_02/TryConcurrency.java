package com.yb.cheung.first_stage.chapter01._02;

import java.util.concurrent.TimeUnit;

public class TryConcurrency {

    public static void main(String[] args) throws Exception{


        /***
         *  1.此时程序发生异常是因为重复启动
         */
        /*Thread thread =  new Thread(){
            @Override
            public void run(){
                try {
                    TimeUnit.SECONDS.sleep(1);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };

        thread.start();
        thread.start();*/


        /**
         *  2.此时程序发生异常是因为线程装换成TERMINATED状态，线程被结束，
         *  所以thread是没有创建线程，无法启动线程所以发生异常
         */
        Thread thread =  new Thread(){
            @Override
            public void run(){
                try {
                    TimeUnit.SECONDS.sleep(1);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };

        thread.start();
        TimeUnit.SECONDS.sleep(1);
        thread.start();

    }



}
