package com.yb.cheung.first_stage.chapter01._01;

import java.util.concurrent.TimeUnit;

public class TryConcurrency {

    public static void main(String[] args){

        /**
         * 情况一：
         *  在main方法执行的时候，也是被一个线程执行，在此方法内线程永远都无法执行第二个方法
         */
        /*browseNews();
        browseMusic();*/


        /**
         * 情况二：
         *  创建一个线程去执行browseNews方法，那么当程序执行时，执行main函数的线程会交替执行这两个方法，
         *  不会循环调用同一个方法
         */
        /*new Thread(){
            @Override
            public void run(){
                browseMusic();
            }
        }.start();

        browseNews();*/

        /***
         *  注意：
         *      1.创建一个线程，并且重写其run方法，将enjoyMusic交个它执行。
         *      2.启动新的线程，只有调用了Thread的start方法，才代表派生了一个新的线程，
         *      否则Thread和其它普通的Java对象没有什么区别，start方法是一个立即返回方法，
         *      并不会让程序陷入阻塞
         */

    }


    private static void browseNews(){

        for (;;){
            System.out.println("Uh-huh , the good News ");
            sleep(1);
        }

    }

    private static void browseMusic(){

        for (;;){
            System.out.println("Uh-huh , the good Music");
            sleep(1);
        }

    }

    private static void sleep(int seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
