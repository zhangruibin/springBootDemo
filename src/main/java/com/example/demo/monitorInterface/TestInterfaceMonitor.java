package com.example.demo.monitorInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
* @ClassName TestInterfaceMonitor
*@Description TODO
*@Author zhangrui
*@Date 2018/12/28 15:50
*@Version 
*/

public class TestInterfaceMonitor extends Interface {
    @Autowired
    private Interface anInterface;
    public static void main(String[] args) {
        String aa = "This is a new url...";
        System.out.println("这是开始调用test(url)方法之前打印的");
        String s = testMo(aa);
        System.out.println("这是开始调用test(url)方法之后打印的"+s);

    }
    public static String testMo(String aa){
        String test =  test(aa);
        return test;
    }
}
