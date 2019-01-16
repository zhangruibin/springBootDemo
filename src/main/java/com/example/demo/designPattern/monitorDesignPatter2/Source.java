/*
package com.example.demo.designPattern.monitorDesignPatter2;


import java.awt.*;
import java.util.Enumeration;
import java.util.Vector;

*/
/**
 * Created by zhangrui on 2019/1/2.
 *//*

*/
/*
* @ClassName Source
*@Description TODO
*@Author zhangrui
*@Date 2019/1/2 14:50
*@Version 
*//*

public class Source {
    private Vector repository = new Vector<>();

    public Source() {

    }

    public void addListener(Listener listener) {

        repository.addElement(listener);
    }

    public void notifyEvent() {

        Enumeration enumer = repository.elements();
        while (enumer.hasMoreElements()) {
            Listener listener = (Listener) enumer.nextElement();
            listener.handleEvent(new Event(this));
        }
    }
}
*/
