package com.example.demo.designPattern.monitorDesignPattern;

import java.util.Observable;

/**
 * Created by zhangrui on 2019/1/2.
 */
/*
* @ClassName BeingWatcher
*@Description TODO
*@Author zhangrui
*@Date 2019/1/2 14:39
*@Version 
*/
public class BeingWatcher extends Observable {
    void counter(int num) {
        for (; num >= 0; num--){
            setChanged();
            notifyObservers(new Integer(num));
            try {
                Thread.sleep(100);
                System.out.println("这是调用了被监视着的方法");
            }catch (InterruptedException e) {
                System.out.println("Sleep interrupeted");
            }
        }
    }
}
