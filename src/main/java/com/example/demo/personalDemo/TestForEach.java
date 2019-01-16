package com.example.demo.personalDemo;

import com.beust.jcommander.internal.Nullable;

import java.util.ArrayList;
import java.util.List;

/*
* @ClassName TestForEach
*@Description TODO
*@Author zhangrui
*@Date 2018/12/24 15:10
*@Version 
*/

public class TestForEach {
    public static void main(String[] args) {
        int a=1,b=2,c=3;
        List list = new ArrayList();
        list.add(a);
        list.add(b);
        list.add(c);
        for (Object x :list){
                if (x.toString().equals("3")){
                    System.out.println("能打印出这个证明循环走完了");
                }else {
                System.out.println("循环不一定走完了");
                continue;
            }
            break;
        }
    }
    @SuppressWarnings("unchecked")
    public static void test(){
        List<?> test  = new ArrayList<>();
    }
}
