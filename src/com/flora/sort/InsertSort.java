package com.flora.sort;

import java.util.Arrays;

/**
 * @Author qinxiang
 * @Date 2022/8/29-下午7:40
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {101,34,119,1};
        insertSort(arr);
        System.out.println(Arrays.toString(arr));
    }
    public static void insertSort(int[] arr){
        //使用逐步推导的方式来讲解，便利理解
        //第1轮{101,34,119,1}=>{34,101,119,1}
        //定义待插入的数
        for(int i = 1;i < arr.length;i++) {
            int insertVal = arr[i];
            int insertIndex = i - 1;//即arr[1]的前面这个数的下标
            //给insertVal找到插入的位置
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            //当退出while循环时，说明插入的位置找到，insertIndex+1
            arr[insertIndex+1] = insertVal;
        }

    }
}
