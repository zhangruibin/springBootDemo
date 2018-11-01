package com.example.main;

/*
* @ClassName FirstMainTest
*@Description TODO 基础测试类
*@Author zhangrui
*@Date 9:17 9:17
*@Version
* */
public class FirstMainTest {
    public static void main(String[] args) {
        //++i与i++的区别以及位移运算符的使用
        testCountFromMT();
    }
    public static void testCountFromMT(){
        int a = 10>>1;
        System.out.println("a的值为："+a);
        int b = a++;
        System.out.println("b的值为："+b);
        int c = ++a;
        System.out.println("c的值为："+c);
        int d = b * a++;
        System.out.println("a的值为："+a);
        System.out.println("b的值为："+b);
        System.out.println("c的值为："+c);
        System.out.println("d的值为："+d);
    }
}
