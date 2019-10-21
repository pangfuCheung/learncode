package com.yb.cheung.first_stage.chapter05._02;

import java.security.spec.ECField;
import java.util.LinkedList;

/**
 * 单线程间通信
 */
public class EventQueue {

    private final int max;

    static class Event{

    }

    private final static int DEFAULT_MAX_EVENT = 10;

    private final LinkedList<Event> eventQueue = new LinkedList<Event>();

    public EventQueue(int max){
        this.max = max;
    }

    public EventQueue(){
        this(DEFAULT_MAX_EVENT);
    }

    public void offer(Event event){
        synchronized (eventQueue){
            if (eventQueue.size() >= max){
                console(" the queue is full.");
                try {
                    eventQueue.wait();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            console(" the new event is submitted.");
            eventQueue.addLast(event);
            eventQueue.notify();
        }
    }

    public Event take(){
        synchronized (eventQueue){
            if (eventQueue.isEmpty()){
                console("the queue is empty.");
                try {
                    eventQueue.wait();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            Event event = eventQueue.getFirst();
            this.eventQueue.notify();
            console(" the Event " + event + "is handled.");
            return event;
        }
    }

    private void console(String message){
        System.out.printf("%s:%s\n",Thread.currentThread().getName(),message);
    }

}
