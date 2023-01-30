package com.flora.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author qinxiang
 * @Date 2022/8/30-下午7:55
 */
public class BinarySearch {
    public static void main(String[] args) {
        int arr[]= {1,8,10,89,1000,1000,1234};//二分查找的前提是该数组是有序的
        ArrayList arrayList = binarySearch2(arr, 0, arr.length - 1, 10000);
        if(arrayList.size() == 0){
            System.out.println("没有找到目标元素");
        }else {
            System.out.println("目标元素的下标为" + arrayList.toString());
        }
    }
    public static int binarySearch(int[] arr, int left, int right, int value){
        if(left > right){
            return -1;//说明递归了整个数组，但是没有找到
        }
        int mid = (left+right)/2;
        int midVal = arr[mid];
        if(value > midVal){
            return binarySearch(arr, mid +1, right,value);
        }else if(value < midVal){
            return binarySearch(arr, left,mid, value);
        }else{
            return mid;
        }
    }
    //找到所有的目标元素
    public static ArrayList binarySearch2(int[] arr, int left, int right, int value){
        if(left > right){
            return new ArrayList();//说明递归了整个数组，但是没有找到,返回一个空数组
        }
        int mid = (left+right)/2;
        int midVal = arr[mid];
        if(value > midVal){
            return binarySearch2(arr, mid +1, right,value);
        }else if(value < midVal){
            return binarySearch2(arr, left,mid, value);
        }else{

            ArrayList<Integer> resIndexList = new ArrayList<>();
            int temp = mid -1;
            while(true){
                if(temp < 0 || arr[temp] != value){
                    break;
                }
                resIndexList.add(temp);
                temp -= 1;
            }
            resIndexList.add(mid);
            //向mid索引值的右边扫描，将所有满足1000的元素的下标加入到集合
            temp = mid + 1;
            while(true){
                if (temp > arr.length - 1 || arr[temp] != value) {

                    break;
                }
                //否则就放入到集合
                resIndexList.add(temp);
                temp += 1;//temp右移
            }
            return resIndexList;
        }
    }
}
