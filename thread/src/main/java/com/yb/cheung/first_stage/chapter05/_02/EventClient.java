package com.yb.cheung.first_stage.chapter05._02;

import java.util.concurrent.TimeUnit;

public class EventClient {

    public static void main(String[] args) {

        final EventQueue eventQueue = new EventQueue();

        new Thread(()->{
            for (;;){
                eventQueue.offer(new EventQueue.Event());
            }
        },"Producer").start();

        new Thread(()->{
            for (;;){
                eventQueue.take();
                try {
                    TimeUnit.SECONDS.sleep(10);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        },"Customer").start();

    }

}
