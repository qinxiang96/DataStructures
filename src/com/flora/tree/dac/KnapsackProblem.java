package dac;

/**
 * @Author qinxiang
 * @Date 2022/9/14-上午9:21
 */
public class KnapsackProblem {
    public static void main(String[] args) {
        int[] w = {1,4,3};//物品的重量
        int[] val = {1500, 3000, 2000};//物品的价值
        int m = 4;//背包的容量
        int n = val.length;//物品的个数
        //创建二维数组，v[i][j]表示前i个物品中能够装入容量为j的背包中的最大价值
        int[][] v = new int[n+1][m+1];
        //为了记录放入商品的情况，我们定一个二维数组
        int[][] path = new int[n+1][m+1];

        //初始化第一行第一列，这里在本程序汇总是可不处理的，默认就是0
        for (int i = 0; i < v.length; i ++){
            v[i][0] = 0;//将第一列设置为0
        }
        for (int j =0;j < v[0].length; j ++){
            v[0][j] = 0;//将第一行设置为0
        }
        //根据前面得到公式来动态规划处理
        for (int i = 1; i < v.length; i ++){//不处理第一行 i从1开始
            for (int j = 1; j < v[0].length; j ++){
                //公式
                if (w[i-1] > j){//因为我们程序是从1开始的，因此原来公式中的w[i]修改成w[i-1]
                    v[i][j]=v[i-1][j];

                }else{
                    //因为我们的i从1开始的，因此公式需要调整
                    //v[i][j] = Math.max(v[i-1][j], val[i-1]+v[i-1][j-w[i-1]]);
                    //为了记录商品存放背包的情况，我们不能简单的使用上面的公式，需要使用if-else来体现公式
                    if (v[i-1][j] < val[i-1]+v[i-1][j-w[i-1]]){
                        v[i][j] = val[i-1]+v[i-1][j-w[i-1]];
                        //把当前的情况记录到path
                        path[i][j] = 1;

                    }else {
                        v[i][j] = v[i-1][j];
                    }
                }
            }
        }
        //输出一下v，看看目前的情况
        for (int i =0; i < v.length; i ++){
            for (int j = 0; j < v[i].length; j ++){
                System.out.print(v[i][j] + " ");
            }
            System.out.println();
        }
        //输出我们放入的是哪些商品
        //遍历path
        //这样输出会把所有的放入情况都得到，其实我们只需要最后的放入
        /*for (int i = 0; i < path.length; i ++){
            for (int j = 0; j <path[i].length; j ++){
                if (path[i][j] == 1){
                    System.out.printf("第%d个商品放入到背包\n", i);
                }
            }
        }

         */
        int i = path.length -1;//行的最大下标
        int j = path[0].length -1;//列的最大下标
        while(i > 0 && j > 0){
            if(path[i][j] == 1){
                System.out.printf("第%d个商品放入到背包\n", i);
                j -= w[i-1];//调整背包的重量
            }
            i --;
        }
        //System.out.println(v[0].length);
    }
}
