package com.flora.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @Author qinxiang
 * @Date 2022/8/30-下午3:48
 */
public class RadixSort {
    public static void main(String[] args) {
        //int arr[] = {53,3,542,748,14,214};
        int[] arr = new int[80000];
        for(int i = 0;i < 80000;i ++){
            arr[i] = (int) (Math.random()*80000);
        }
        System.out.println("排序前");
        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String data1Str = simpleDateFormat.format(data1);
        System.out.println("排序前的时间是="+data1Str);


        radixSort(arr);
        Date data2 = new Date();
        String data2Str = simpleDateFormat.format(data2);
        System.out.println("排序后的时间是="+data2Str);

    }
    //基数排序方法
    public static void radixSort(int[] arr){

        //得到数组中最大的数的位数
        int max = arr[0];//假设第一数就是最大数
        for(int i = 1; i < arr.length; i++){
            if (arr[i] > max){
                max = arr[i];
            }
        }
        //得到最大数是几位数
        int maxLength = (max + "").length();
        //第1轮针对每个元素的个位进行排序处理
        //定义一个二维 数组，表示10个桶，每个桶就是一个一维数组
        int[][] bucket = new int[10][arr.length];//二维数组包含10个一维数组，为了防止在放入数的时候，数据溢出，则每个一维数组（桶），大小定为arr.length；很明确基数排序是使用空间换时间的经典算法
        //为了记录每个桶中，实际存放了多少个数据，我们定义一个一维数组来记录各个桶的每次放入的数据的个数
        int[] bucketElementCounts = new int[10];

        //这里我们使用循环将代码处理
        for(int i = 0, n =1; i < maxLength; i ++, n*=10) {


            //bucketElementCounts[0]记录的就是bucket[0]桶的放入数据的个数
            for (int j = 0; j < arr.length; j++) {
                //取出每个元素的个位的值
                int digitOfElement = arr[j] / n % 10;
                //放入到对应的桶中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;
            }
            //按照这个桶的顺序，一维数组的下标依次取出数据，放入原来的数组
            int index = 0;
            //遍历每一桶，并将桶中的数据放入到原数组
            for (int k = 0; k < bucketElementCounts.length; k++) {
                //如果桶中有数据，我们才放入到原数组
                if (bucketElementCounts[k] != 0) {
                    //循环该桶即第k个桶放入
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        //取出元素放入到arr
                        arr[index++] = bucket[k][l];
                    }
                }
                //第i+1轮处理后，需要将每个bucketElementCounts[k] = 0
                bucketElementCounts[k] = 0;
            }
            //System.out.println("第"+(i+1)+"轮，对个位的排序处理arr=" + Arrays.toString(arr));
        }
    }
}
