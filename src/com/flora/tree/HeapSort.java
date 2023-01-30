package com.flora.tree;

import java.util.Arrays;

/**
 * @Author qinxiang
 * @Date 2022/9/2-下午4:31
 */
public class HeapSort {
    public static void main(String[] args) {
        //要求将数组进行升序排序
        int arr[] = {4,6,8,5,9};
        heapSort(arr);
    }
    //编写一个堆排序的方法
    public static void heapSort(int arr[]){
        System.out.println("堆排序");
        int temp = 0;
        for(int i = arr.length /2 -1; i >=0;i --) {
            adjustHeap(arr, i, arr.length);
        }
        //将堆顶元素与末尾元素交换，将最大元素沉到数组末端
        //重新调整结构，使其满足堆定义，然后继续交换堆顶元素和当前末尾元素，反复执行调整+交换步骤，直到整个序列有序
        for(int j = arr.length -1; j >0;j --){
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, j);
        }
        System.out.println(Arrays.toString(arr));
    }
    //将一个数组二叉树，调整成一个大顶堆

    /**
     * 功能：完成将以i对应的非叶子节点的树调整为大顶堆
     * @param arr 待调整的数组
     * @param i 表示非叶子节点在数组中索引
     * @param lenght 表示对多少个元素继续调整，length是在逐渐减少
     */
    public  static void adjustHeap(int[] arr, int i , int lenght){
        int temp = arr[i];//先取出当前元素的值，保存在临时变量
        //开始调整
        //说明
        for(int k = i * 2 + 1;k < lenght; k = k*2 + 1){
            if(k + 1 < lenght && arr[k] < arr[k+1]){
                //说明左子节点的值小于右子节点的值
                k++;//k指向右子节点
            }
            if(arr[k] > temp){//如果子节点大于父节点
                arr[i] = arr[k];//把较大的值赋给当前节点
                i = k;//继续循环比较

            }else{
                break;
            }
        }
        //当for循环结束后，我们已经将以i为父节点的树的最大值放在了最顶（局部）
        arr[i] = temp;//将temp值放到调整后的位置
    }
}
