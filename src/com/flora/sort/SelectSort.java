package com.flora.sort;

import java.util.Arrays;

/**
 * @Author qinxiang
 * @Date 2022/8/29-下午5:16
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] arr = {101,34,119,1};
        selectSort(arr);
    }
    //选择排序
    public static void selectSort(int[] arr){
        //使用逐步推导的方式来讲解选择排序
        //原始的数组：101，34，119，1
        //找到最小的，和第一个数换位置
        for(int i = 0; i < arr.length -1; i ++) {
            int minIndex = i;
            int min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {
                    min = arr[j];
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
            System.out.println(Arrays.toString(arr));
        }
    }
}
