package com.flora.sparsearray;

import java.io.*;

/**
 * @Author qinxiang
 * @Date 2022/8/23-下午6:16
 */
public class SparseArray {
    public static void main(String[] args) {
        //创建一个原始的二维数组11*11
        //0表示没有棋子 1表示黑子 2表示蓝子
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][2] = 2;
        //输出原始的二维数组
        for (int[] row : chessArr1){
            for (int data : row){
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
        //将二维数组转稀疏数组的思路
        //1 先遍历二维数组 得到非0数据的个数
        int sum = 0;
        for(int i = 0; i < chessArr1.length; i ++){
            for(int j = 0; j < chessArr1.length; j ++){
                if(chessArr1[i][j] != 0){
                    sum ++;
                }
            }
        }
        //System.out.println("sum="+sum);
        //2。创建对应的稀疏数组
        int sparseArr[][] = new int[sum +1][3];
        //给稀疏数组赋值
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;
        //遍历二维数组。将非0的值存放到sparseArr中
        int count = 0;//用于记录是第几个非0数据
        for (int i = 0;i <chessArr1.length; i ++){
            for(int j=0; j < chessArr1.length; j ++){
                if(chessArr1[i][j] != 0){
                    count ++;
                    sparseArr[count][0]=i;
                    sparseArr[count][1]=j;
                    sparseArr[count][2]=chessArr1[i][j];
                }
            }
        }


        //输出稀疏数组
        System.out.println();
        try {
            OutputStream f = new FileOutputStream("/Users/qinxiang/xishushuzu");
            OutputStreamWriter writer = new OutputStreamWriter(f, "UTF-8");
            for(int i = 0; i < sparseArr.length; i ++){
                for (int j =0; j < sparseArr.length; j ++){
                    writer.write(sparseArr[i][j] + "\t");
                }
                writer.write("\n");

            }
            writer.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(int i = 0; i < sparseArr.length; i ++){
            System.out.printf("%d\t%d\t%d\n", sparseArr[i][0],sparseArr[i][1],sparseArr[i][2]);
        }
        System.out.println();

        //将稀疏数组恢复成原始的二维数组
        int chessArr2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];
        for (int i =1; i < sparseArr.length; i ++){
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        //chessArr2[sparseArr[1][0]][sparseArr[1][1]] = sparseArr[1][2];
        //chessArr2[sparseArr[2][0]][sparseArr[2][1]] = sparseArr[2][2];
        for (int arr[] : chessArr2){
            for (int data : arr){
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }


    }
}
