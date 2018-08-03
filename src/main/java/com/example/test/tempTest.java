package com.example.test;

/**
 * Created by zhangrui on 2018/8/1.
 */
/*
* @ClassName tempTest
*@Description TODO
*@Author zhangrui
*@Date 16:57 16:57
*@Version 
* */
public class tempTest {
    public static void main(String[] args) {
        int i = 0,j = 0,flag = 0;
        for (int temp = 4;flag < temp;flag++){
            if (flag/2 == 1){
                i = 1;
            }
            if (flag%2 == 1){
                j = 1;
            }
            soutString(i,j);
            i = 0;j = 0;
        }
    }
    public static void soutString(int i,int j){
        String s1 = "",s2 = "",s3 = "",s4 = "";
        if(i == 0){
            s1 = "不告";
        }
        if(j == 0){
            s2 = "不拿";
        }
        if(i == 1){
            s3 = "告而";
        }
        if(j == 1){
            s4 = "拿之";
        }
        if (i == 0 && j == 0){
            System.out.println(s1+s2+"謂之慫");
        }else if(i == 0 && j == 1){
            System.out.println(s1+s4+"謂之竊");
        }else if (i == 1 && j == 1){
            System.out.println(s3+s4+"謂之借");
        }else if(i == 1 && j == 0){
            System.out.println(s3+s2+"謂之傻逼");
        }
    }
}
