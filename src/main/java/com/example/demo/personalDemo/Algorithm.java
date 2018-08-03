package com.example.demo.personalDemo;

/**
 * Created by zhangrui on 2018/2/22.
 */
/*
* @ClassName Algorithm
*@Description TODO
*@Author zhangrui
*@Date 14:37 14:37
*@Version 
* */
public class Algorithm {
    /**
    * @author zhangrui
    * @Description 算法测试:二分法,前提是已经大小排好序的
    * @Date 14:37 2018/2/22
    * @Param 
    * @returu 
    **/
    public static void main(String[] args) {
        int[] arr = new int[] { 12, 23, 34, 45, 56, 67, 77, 89, 90 };
        System.out.println(search(arr, 12));
        System.out.println(search(arr, 45));
        System.out.println(search(arr, 67));
        System.out.println(search(arr, 89));
        System.out.println(search(arr, 99));
    }

    public static int search(int[] arr, int key) {
        int start = 0;
        int end = arr.length - 1;
        while (start <= end) {
            int middle = (start + end) / 2;
            if (key < arr[middle] ) {
                end = middle - 1;
            } else if (key > arr[middle]) {
                start = middle + 1;
            } else {
                return middle;
            }
        }
        return -1;
    }
    /**
    * @author zhangrui
    * @Description TopK算法n*logN
    * @Date 9:01 2018/2/23
    * @Param 
    * @returu 
    **/
    public void  test(){

    }
}
