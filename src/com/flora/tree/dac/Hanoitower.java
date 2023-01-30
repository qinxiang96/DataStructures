package dac;

/**
 * @Author qinxiang
 * @Date 2022/9/13-下午5:06
 */
public class Hanoitower {
    public static void main(String[] args) {
        hanoiTower(5,'A','B', 'C');
    }
    //汉诺塔的移动方法
    //使用分治算法
    public static void hanoiTower(int num, char a, char b, char c){
        //如果只有一个盘
        if(num == 1){
            System.out.println("第1个盘从" +a+"->"+c);
        }else {
            //如果我们从n>=2的情况，我们总是可以看做是两个盘，最下边的盘和最上面的所有盘
            //1、先把最上面的所有盘a->b 移动过程会使用到c
            hanoiTower(num - 1,a,c,b);
            //2、把最下边的盘a->c
            System.out.println("第" +num+"个盘从"+a+"->" +c);
            //3、把b塔的所有盘从b->c，移动过程使用到a塔
            hanoiTower(num - 1, b,a,c);
        }
    }
}
