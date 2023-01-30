package com.flora.search;

/**
 * @Author qinxiang
 * @Date 2022/8/30-下午7:22
 */
public class SeqSearch {
    public static void main(String[] args) {
        int arr[] = {1,9,11,-1,34,89};
        int i = sqeSearch(arr, -1);
        if(i == -1){
            System.out.println("没有找到目标元素");
        }
        System.out.println("目标元素的下标为"+i);
    }
    public static int sqeSearch(int[] arr, int value){
        for(int i = 0;i < arr.length; i++){
            if(value == arr[i]){
                return i;
            }
        }
        return -1;
    }
}
