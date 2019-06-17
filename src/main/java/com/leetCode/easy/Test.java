package com.leetCode.easy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName Test
 * @Description TODO
 * @Author Administrator
 * @Date 2019/3/13 16:37
 * @Version
 */
public class Test {
    public static void main(String[] args) {
        int[][] test =new int[][]{};
        int x ;
        int y ;
        int num;

        int a = add(4);
        System.out.println("本层最大值为："+a+"坐标"+"(-"+a+",-"+a+")");

    }
    public static int test(int x,int y){
        if (x==y && x>0){

        }
        return  0;
    }
    public static int add(int z){
        List<Integer> list = new ArrayList();
        int tempNum = 0;
        int x = 2;
        int maxNum = 0;
        list.add(0);
        list.add(4);
        for (;x<z;x++){
            if (list.size() >= 1){
                tempNum = 4*(z+1) + list.get(x-1);
            }else {
                tempNum = 4*(z+1) ;
            }

            list.add(tempNum);
            Collections.sort(list);
        }

        System.out.println(list.toString());
        return maxNum;
    }
}
