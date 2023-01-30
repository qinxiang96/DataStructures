package com.flora.search;

import java.util.Arrays;

/**
 * @Author qinxiang
 * @Date 2022/8/31-上午10:06
 */
public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for(int i = 0; i < 100; i ++){
            arr[i] = i +1;
        }
        int index = insertValueSearch(arr, 0, arr.length - 1, 100);
        System.out.println(index);
        //System.out.println(Arrays.toString(arr));
    }
    //编写插值查找算法 要求数组是有序的
    public static int insertValueSearch(int[] arr, int left, int right, int findVal){
        if(left > right || findVal < arr[0] || findVal > arr[arr.length - 1]){//防止越界
            return -1;
        }
        //求出mid
        int mid = left + (right - left)*(findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if(findVal > midVal){
            //向右递归
            return insertValueSearch(arr, mid+1,right,findVal);
        }else if(findVal < midVal){
            return insertValueSearch(arr,left,mid -1,findVal);
        }else{
            return mid;
        }
    }
}
