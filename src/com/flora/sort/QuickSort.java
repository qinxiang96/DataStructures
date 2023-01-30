package com.flora.sort;

import java.util.Arrays;

/**
 * @Author qinxiang
 * @Date 2022/8/30-上午10:29
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {-9,78,0,23,-567,70};
        quickSort(arr, 0, arr.length-1);
        System.out.println(Arrays.toString(arr));
    }
    public static void quickSort(int[] arr, int left, int right){
        int l = left;
        int r = right;
        int pivot  = arr[(left + right) / 2];//中轴值
        int temp = 0;
        while(l < r){
            while(arr[l] < pivot){
                l += 1;
            }
            while(arr[r] > pivot){
                r -= 1;
            }
            if (l >= r){
                break;
            }
            temp = arr[l];
            arr[l] = arr[r];
            arr[r]  = temp;
            //如果交换完后，发现这个arr[1] == pivot值相等--，前移
            if(arr[1] == pivot){
                r -= 1;
            }
            if (arr[r] == pivot){
                l += 1;
            }

        }
        //如果l == r，必须l++,r--,否则为出现栈溢出
        if(l == r){
            l+=1;
            r-=1;
        }
        //向左递归
        if(left < r){
            quickSort(arr, left, r);
        }
        //向右递归
        if(right > l){
            quickSort(arr, l, right);
        }
    }
}
