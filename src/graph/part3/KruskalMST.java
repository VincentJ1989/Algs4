package graph.part3;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.UF;

/**
 * 最小生成树Kruskal算法<br></br>
 * 主要思想：按照边的权重顺序(从小到大)处理，将边加入最小生成树中，加入的边不会构成环，知道树中含有V-1条边为止<br></br>
 * Kruskal算法能够计算任意加权连通图的最小生成树.
 */
public class KruskalMST {
    private Queue<Edge> mst;

    public KruskalMST(EdgeWeightedGraph G) {
        mst = new Queue<>();
        MinPQ<Edge> pq = new MinPQ<>();
        // 加到队列中
        for (Edge e : G.edges()) {
            pq.insert(e);
        }
        UF uf=new UF(G.V());
        while(!pq.isEmpty()&&mst.size()<G.V()-1){
            // 从pq中获取权重最小的边和它的顶点
            Edge e=pq.delMin();
            int v=e.either();
            int w=e.other(v);
            // 使用UF过滤成环--忽略失效的边
            if(uf.connected(v,w)){
                continue;
            }
            // 合并分量
            uf.union(v,w);
            // 将边添加到最小生成树中
            mst.enqueue(e);
        }
    }

    public Iterable<Edge>edges(){
        return mst;
    }
    // public double weight(){
    // // TODO: 2018-12-11 练习4.3.31
    // }
}
