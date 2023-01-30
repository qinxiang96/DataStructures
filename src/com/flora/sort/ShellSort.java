package com.flora.sort;

import java.util.Arrays;

/**
 * @Author qinxiang
 * @Date 2022/8/29-下午10:05
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {8,3,5,3,2,6};
        shellSort(arr);
        System.out.println(Arrays.toString(arr));
    }
    public static void shellSort(int[] arr){
        int temp = 0;
        for(int gap = arr.length/2; gap>0; gap/=2){
            for (int i = gap; i < arr.length; i ++) {
                //遍历各组中所有的元素（共gap组，每组有 个元素），步长gap
                for (int j = i - gap; j>=0; j -= gap){
                    //如果当前元素大于加上步长后的那个元素，说明交换
                    if(arr[j] > arr[j+gap]){
                        temp = arr[j];
                        arr[j] = arr[j+gap];
                        arr[j+gap] = temp;

                    }
                }
            }
            System.out.println(Arrays.toString(arr));

        }
    }
}
