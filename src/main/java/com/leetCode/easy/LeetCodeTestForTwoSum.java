package com.leetCode.easy;

import java.util.HashMap;
import java.util.Map;

/*
* @ClassName LeetCodeTestForEasyPro
*@Description TODO leetCode刷题，简单级别-（两数之和-Two sum）
* 题目：给定一个整数数组和一个目标值，找出数组中和为目标值的两个数。
*你可以假设每个输入只对应一种答案，且同样的元素不能被重复利用。
*示例:
*给定 nums = [2, 7, 11, 15], target = 9
*因为 nums[0] + nums[1] = 2 + 7 = 9
*所以返回 [0, 1]
*@Author zhangrui
*@Date 10:38 10:38
*@Version 
* */
public class LeetCodeTestForTwoSum {
    public static void main(String[] args) {
        int[] nums = {3,2,4};
        int target = 6;
        int[] ints = twoSum(nums, target);
        System.out.println("初始数组为："+toString(nums)+",目标数为："+target+",得到的结果数组是："+toString(ints)+"。");
        int[] ints2 = twoSum2(nums, target);
        System.out.println("初始数组为："+toString(nums)+",目标数为："+target+",得到的结果数组2是："+toString(ints2)+"。");
    }
    public static int[] twoSum(int[] nums, int target) {
        int l = nums.length;
        int[] newNums = new int[l] ;
        int[] result = null;
        int[] result2 = null;
        boolean flag = false;
        int count = 0;
        int tempNum ;
        for(int i = 0;i<nums.length;i++){
            if(nums[i] <= target){
                tempNum = nums[i];
                newNums[count] = tempNum;
                count++;
            }
        }
        //找出这两个数的值，简化时间复杂度
        for(int j = 0;j<newNums.length;j++){
            for(int z = j+1;z<newNums.length;z++){
                if( target == newNums[j] +newNums[z]){
                    result = new int[2];
                    flag = true;
                    result[0] = newNums[j];
                    result[1] =newNums[z];
                    break;
                }
            }
            if (flag){
                break;
            }
        }
        //找到这两个数在原数组的数组下标
        //暴力破解，时间复杂度：O(n^2)，空间复杂度：O(1)
        for(int j = 0;j<nums.length;j++){
            //重置标志器
            flag = false;
            for(int z = j+1;z<nums.length;z++){
                if( target == nums[j] + nums[z]){
                    result2 = new int[2];
                    flag = true;
                    result2[0] = j;
                    result2[1] = z;
                    break;
                }
            }
            if (flag){
                break;
            }
        }
        return result2;
    }
    public static String toString(int[] ints){
        String string = "" ;
        string += "[";
        for (int i = 0;i<ints.length;i++){
            if (i == ints.length-1){
                string += ints[i];
            }else {
                string += ints[i]+",";
            }
        }
        string += "]";
        return string.toString();
    }
    //使用哈希表进行值和下标的存储，使用containsKey进行判断，用空间换时间，时间复杂度：O(n)，空间复杂度：O(n)
    public static int[] twoSum2(int[] nums, int target){
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}
