package com.flora.recursion;

/**
 * @Author qinxiang
 * @Date 2022/8/28-下午6:13
 */
public class Queue8 {
    //定义一个max表示共有多少个皇后
    int max = 8;
    //定义数组array，保存皇后放置位置的结果，比如arr={0,4,7,5,2,6,1,3}
    int[] array = new int[max];
    public static void main(String[] args) {
        Queue8 queue8 = new Queue8();
        queue8.check(0);

    }
    //编写一个方法，放置第n个皇后
    private void check(int n){
        if(n == max){
            print();
            return;
        }
        //依次放入皇后，并判断是否冲突
        for (int i = 0; i < max; i ++){
            //先把当前这个皇后n，放到该行的第1列
            array[n] = i;
            //判断当放置第n个皇后到i列时，是否冲突
            if (judge(n)){//不冲突
                //接着放n+1个皇后，即开始递归
                check(n + 1);
            }
            //如果冲突，就继续执行array[n] = i;即将第n个皇后放置在本行的后移的一个位置


        }
    }
    //查看当我们放置第n个皇后，就去检测该皇后是否和前面已经摆放的皇后冲突
    private boolean judge(int n){
        for (int i =0; i < n; i ++){
            if (array[i] == array[n] || Math.abs(n -i) == Math.abs(array[n] - array[i])){
                return false;
            }
        }
        return true;
    }
    //写一个方法，可以将皇后摆放的位置输出
    private void print(){
        for (int i = 0;i < array.length; i ++){
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
