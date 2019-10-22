package com.yb.cheung.first_stage.chapter03;

import java.util.concurrent.TimeUnit;

public class TicketWindowRunnable implements Runnable{

    private int index = 1;

    private final static int MAX = 500;

    private final static Object MUTEX = new Object();

    public void run() {

        synchronized (MUTEX){

            while (index <= MAX){
                System.out.println(Thread.currentThread() + " 的号码是 " + (index ++));
                try {
                    TimeUnit.SECONDS.sleep(1);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }

    }

    public static void main(String[] args) {

        final TicketWindowRunnable task = new TicketWindowRunnable();

        Thread t1 = new Thread(task,"一号窗口");
        Thread t2 = new Thread(task,"二号窗口");
        Thread t3 = new Thread(task,"三号窗口");
        Thread t4 = new Thread(task,"四号窗口");

        t1.start();
        t2.start();
        t3.start();
        t4.start();

    }

}
