package com.yb.cheung.first_stage.chapter03;

import java.util.concurrent.TimeUnit;

public class Mutex {

    private final static Object MUTEX = new Object();

    public void accessResource(){

        synchronized (MUTEX){

            try {
                TimeUnit.SECONDS.sleep(10);

            }catch (InterruptedException e){
                e.printStackTrace();
            }

        }

    }


    public static void main(String[] args) {

        final Mutex mutex = new Mutex();
        for (int i=0;i<5;i++){
            new Thread(mutex::accessResource).start();
        }

    }

}
