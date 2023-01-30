package com.flora.sort;

import java.util.Arrays;

/**
 * @Author qinxiang
 * @Date 2022/8/30-下午12:08
 */
public class MergetSort {
    public static void main(String[] args) {
        int arr[] = {8,4,5,6,1,3,6,2};
        int temp[] = new int[arr.length];//归并排序需要一个额外空间
        mergeSort(arr,0,arr.length-1,temp);
        System.out.println(Arrays.toString(arr));
    }
    //分+合方法
    public static void mergeSort(int[] arr, int left,int right, int[] temp){
        if(left < right){
            int mid = (left + right) / 2;
            //向左递归进行分解
            mergeSort(arr, left,mid,temp);
            //向右递归进行分解
            mergeSort(arr, mid+1, right,temp);
            //合并
            merge(arr,left,mid,right,temp);
        }
    }
    //合并的方法
    public static void merge(int[] arr, int left, int mid, int right, int[] temp){
        int i = left;//初始化i，左边有序序列的索引
        int j = mid + 1;
        int t = 0;//指向temp数组的当前索引
        //先把左右两边有序的数据按照规则填充到temp数组
        //直到左右两边的有序序列，有一边处理完毕为止
        while(i <= mid && j <= right){
            if(arr[i] <= arr[j]){
                temp[t] = arr[i];
                t += 1;
                i += 1;
            }else{
                temp[t] = arr[j];
                t += 1;
                j += 1;
            }
        }
        //把有剩余数据的一边的数据依次全部填充到temp
        while(i <= mid){
            //左边的有序序列还有剩余的元素，就全部填充到temp
            temp[t] = arr[i];
            t += 1;
            i += 1;
        }
        while(j <= right){
            //右边的有序序列还有剩余的元素，就全部填充到temp
            temp[t] = arr[j];
            t += 1;
            j += 1;
        }
        //将temp数组的元素拷贝到arr
        //注意并不是每次都拷贝所有
        t = 0;
        int tempLeft = left;
        while(tempLeft <= right){//第一次合并tempLeft = 0,right = 1//tempLeft=2,right = 3;
            arr[tempLeft] = temp[t];
            t += 1;
            tempLeft += 1;
        }
    }
}
