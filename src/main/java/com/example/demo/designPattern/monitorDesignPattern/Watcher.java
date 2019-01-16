package com.example.demo.designPattern.monitorDesignPattern;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by zhangrui on 2019/1/2.
 */
/*
* @ClassName Watcher
*@Description TODO
*@Author zhangrui
*@Date 2019/1/2 14:38
*@Version 
*/
public class Watcher implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        System.out.println(o.toString()+arg.toString());
        System.out.println("update() is called!");
    }
}
