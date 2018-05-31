package com.example.demo;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhangrui on 2018/1/9.
 */
public class Thread1 implements Runnable {
    private  static final int MAX_PRODUCT  = 9;
    volatile String s = "";
    Object lock;
    ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
   /* BlockingDeque<String> blockingDeque = new BlockingDeque<String>(){

    };*/
    @Override
    public synchronized void run() {
        synchronized (lock){
            System.out.println("this is just for test thread one 1");
        }
        System.out.println("this is just for test thread one 2");
    }

    public static void main(String[] args) {
        // ExecutorService executorService = Executors.newCachedThreadPool();
         //ExecutorService executorService = Executors.newSingleThreadExecutor();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.execute(new Thread());

    }

}
