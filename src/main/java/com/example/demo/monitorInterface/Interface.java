package com.example.demo.monitorInterface;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/*
* @ClassName Interface
*@Description TODO
*@Author zhangrui
*@Date 2018/12/28 15:43
*@Version 
*/
public abstract class Interface {
    @Before("monitorInterface()")
    protected static String test(String url){
        String result = "";
        if (StringUtils.isNoneEmpty(url)){
            result = url;
        }
        return result;
    }

    @Pointcut("@annotation(ServiceMonitor)")
    protected void monitorInterface(){
        System.out.println("这是切面里面的方法。打印现在的系统时间："+System.currentTimeMillis());
    }

}
