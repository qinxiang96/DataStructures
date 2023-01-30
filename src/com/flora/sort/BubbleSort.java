package com.flora.sort;

import java.util.Arrays;

/**
 * @Author qinxiang
 * @Date 2022/8/29-下午2:37
 */
public class BubbleSort {
    public static void main(String[] args) {
        int arr[] = {3,9,-1,10,-2};
        bubbleSort(arr);
        System.out.println( Arrays.toString(arr));



        //第二趟排序，就是将第二大的数排在倒数第二位
        /*for (int j = 0; j < arr.length - 1-1; j ++){
            if(arr[j] > arr[j + 1]){
                temp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = temp;
            }
        }
        System.out.println("第二趟排序后的数组"+ Arrays.toString(arr));

         */
    }
    public static void bubbleSort(int[] arr){
        int temp = 0;
        boolean flag = false;//标识变量，表示是否进行过交换
        //为了容易理解，我们把冒泡排序的演变过程给大家演示
        //第一趟排序，就是将最大的数排到最后
        for(int i = 0;i < arr.length - 1;i++) {
            for (int j = 0; j < arr.length - 1-i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if(!flag){
                break;
            }else{
                flag = false;//重置flag，进行下次判断
            }
        }
    }
}
