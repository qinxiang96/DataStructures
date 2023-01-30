package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * @Author qinxiang
 * @Date 2022/9/9-下午12:23
 */
public class Graph {
    private ArrayList<String> vertexList;//存储顶点集合
    private int[][] edges;//存储图对应的邻结矩阵
    private int numOfEdges;//表示边的数目
    //定义数组记录某个节点是否被访问
    private boolean[] isVisited;
    public static void main(String[] args) {
        //测试
        int n = 5;//节点的个数
        String Vertexs[] = {"A","B","C","D","E"};
        Graph graph = new Graph(n);
        //循环的添加顶点
        for(String vertex : Vertexs){
            graph.insertVertex(vertex);
        }
        //添加边
        graph.insertEdge(0,1,1);
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,2,1);
        graph.insertEdge(1,3,1);
        graph.insertEdge(1,4,1);
        //显示邻结矩阵
        graph.showGraph();
        //测试深度遍历
        System.out.println("深度遍历");
        graph.bfs();
    }
    //构造器
    public Graph(int n){
        //初始化矩阵和vertexList
        edges = new int[n][n];
        vertexList = new ArrayList<String>(n);
        numOfEdges = 0;
        isVisited = new boolean[5];
    }
    //得到第一个邻接节点的下标
    public int getFirstNeighbor(int index){
        for(int j = 0; j < vertexList.size(); j ++){
            if(edges[index][j] > 0){
                return j;
            }
        }
        return -1;
    }
    //根据前一个邻结节点的下标来获取下一个邻结节点
    public int getNextNeighbor(int v1, int v2){
        for (int j = v1+1; j < vertexList.size(); j ++){
            if (edges[v1][j]>0){
                return j;
            }
        }
        return -1;
    }
    //深度优先遍历算法
    //i 第一次就是0
    public void dfs(boolean[] isVisited, int i){
        //首先我们访问该节点，输出
        System.out.println(getValueByIndex(i) + "->");
        //将节点设置成已经访问
        isVisited[i] = true;
        //查找节点v的第一个邻结节点w
        int w = getFirstNeighbor(i);
        while(w != -1){
            //说明有
            if (!isVisited[w]){
                dfs(isVisited, w);
            }
            //如果w节点已经被访问过
            w = getNextNeighbor(i,w);
        }
    }
    //对dfs进行一个重载，遍历我们所有的节点，并进行dfs
    public void dfs(){
        isVisited = new boolean[5];
        //遍历所有的节点，进行dfs回溯
        for (int i = 0; i < getNumOfVertex(); i ++){
            if(!isVisited[i]){
                dfs(isVisited, i);
            }
        }
    }
    //对一个节点进行广度优先遍历的方法
    private void bfs(boolean[] isVisited, int i){
        int u;//表示队列的头节点对应下标
        int w;//邻接节点w
        //队列，记录节点访问的顺序
        LinkedList queue = new LinkedList();
        //访问节点，输出节点信息
        System.out.println(getValueByIndex(i)+"->");
        //标记为已访问
        isVisited[i] = true;
        //将节点加入队列
        queue.addLast(i);
        while(!queue.isEmpty()){
            //取出队列的头节点下标
            u = (Integer) queue.removeFirst();
            //得到第一个邻接节点的下标w
            w = getFirstNeighbor(u);
            while(w != -1){
                //找到
                //是否访问过
                if(!isVisited[w]){
                    System.out.print(getValueByIndex(w) + "->");
                    //标记已经访问
                    isVisited[w] = true;
                    //入队
                    queue.addLast(w);
                }
                //以u为前驱点，找w后面的下一个邻节点
                w = getNextNeighbor(u, w);//体现出我们的广度优先
            }
        }

    }
    //遍历所有的节点，都进行广度优先搜索
    public void bfs(){
        isVisited = new boolean[5];
        //遍历所有的节点，进行dfs回溯
        for (int i = 0; i < getNumOfVertex(); i ++){
            if(!isVisited[i]){
                bfs(isVisited, i);
            }
        }
    }
    //图中常用的方法
    //返回节点的个数
    public int getNumOfVertex(){
        return vertexList.size();
    }
    //显示图对应的矩阵
    public void showGraph(){
        for(int[] link : edges){
            System.out.println(Arrays.toString(link));
        }
    }
    //得到边的数目
    public int getNumOfEdges(){
        return numOfEdges;
    }
    //返回节点i下标对应的数据 0->A 1->B 2->C
    public String getValueByIndex(int i){
        return vertexList.get(i);
    }
    //返回v1和v2的值
    public int getWeight(int v1, int v2){
        return edges[v1][v2];
    }
    //插入节点
    public void insertVertex(String vertex){
        vertexList.add(vertex);
    }
    //添加边

    /**
     *
     * @param v1 表示点的下标即是第几个顶点 A-B A->0 B->1
     * @param v2 第二个顶点对应的下标
     * @param weight 表示0或1
     */
    public void insertEdge(int v1, int v2,int weight){
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }
}
