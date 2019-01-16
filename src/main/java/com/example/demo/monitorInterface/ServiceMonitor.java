package com.example.demo.monitorInterface;

/*
* @ClassName ServiceMonitor
*@Description TODO
*@Author zhangrui
*@Date 2018/12/28 15:47
*@Version 
*/
public @interface ServiceMonitor {
    String description()  default "";
}
