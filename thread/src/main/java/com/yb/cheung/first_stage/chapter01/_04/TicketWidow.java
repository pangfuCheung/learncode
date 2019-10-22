package com.yb.cheung.first_stage.chapter01._04;

import java.util.concurrent.TimeUnit;

public class TicketWidow extends Thread {

    //柜台名称
    private final String name;

    //最多受理50笔业务
    private static final int Max = 50;

    private static int index = 1;

    public TicketWidow(String name){
        this.name = name;
    }

    @Override
    public void run() {
        while (index<=Max){
            System.out.println("柜台：" + name + "当前号码：" + (index++));
        }
    }

    public static void main(String[] args) {

        TicketWidow t1 = new TicketWidow("1");
        t1.start();
        TicketWidow t2 = new TicketWidow("2");
        t2.start();
        TicketWidow t3 = new TicketWidow("3");
        t3.start();
        TicketWidow t4 = new TicketWidow("4");
        t4.start();
    }
}
