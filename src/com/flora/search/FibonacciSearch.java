package com.flora.search;

import java.util.Arrays;

/**
 * @Author qinxiang
 * @Date 2022/8/31-上午10:53
 */
public class FibonacciSearch {
    public static int maxSize = 20;
    public static void main(String[] args) {
        int[] arr = {1,8,10,89,1000,1234};
        int res = fibSearch(arr, 89);
        System.out.println(res);
    }
    //因为后面我们mid=low+F(k-1)-1,需要使用到斐波那契数列，因此我们需要先获取到一个斐波那契数列
    public static int[] fib(){
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for(int i = 2; i < maxSize; i ++){
            f[i] = f[i-1] + f[i-2];
        }
        return f;
    }
    //编写斐波那契查找算法
    public static int fibSearch(int[] a, int key){
        int low = 0;
        int high = a.length -1;
        int k = 0;// 表示斐波那契分割数值的下标
        int mid = 0;//存放Mid值
        int f[] = fib();
        while(high > f[k] -1){
            k++;
        }
        //因为f(k)值可能大于a的长度，因此我们需要使用Arrays类，构造一个新的数组，并指向temp[]
        int[] temp = Arrays.copyOf(a, f[k]);
        //不足的部分会使用0填充，实际上需要使用a数组最后的数值填充temp
        for(int i = high + 1; i < temp.length; i ++){
            temp[i] = a[high];
        }
        //使用while来循环处理吗、，找到我们的数key
        while(low <= high){
            mid = low +f[k-1] -1;
            if(key < temp[mid]){
                //我们应该继续向数组的前面查找 左边
                high = mid -1;
                k--;
            }else if(key > temp[mid]){
                //我们应该继续向数组的前面查找 右边
                low = mid +1;
                k -= 2;//
            }else{
                if(mid <= high){
                    return mid;
                }else{
                    return high;
                }
            }
        }
        return -1;
    }
}
