package graph.part3;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;

/**
 * 最小生成树的Prim算法的延时实现<br></br>
 * Prim算法能得到任意加权连通图的最小生成树。
 */
public class LazyPrimMST {
    /**最小生成树的顶点*/
    private boolean[] marked;
    /**最小生成树的边*/
    private Queue<Edge> mst;
    /**横切边(包括失效的边)*/
    private MinPQ<Edge> pq;

    public LazyPrimMST(EdgeWeightedGraph G) {
        pq = new MinPQ<>();
        marked = new boolean[G.V()];
        mst = new Queue<>();
        // 假设G是连通的
        visit(G, 0);
        while (!pq.isEmpty()) {
            // 从pq中得到权重最小的边
            Edge e = pq.delMin();
            int v = e.either();
            int w = e.other(v);
            if (marked[v] && marked[w]) {
                continue;
            }
            mst.enqueue(e);
            if (!marked[v]) {
                visit(G, v);
            }
            if (!marked[w]) {
                visit(G, w);
            }
        }
    }

    private void visit(EdgeWeightedGraph G, int v) {
        // 标记顶点v并将所有连接v和未被标记顶点的边加入pq---包含失效的边
        marked[v] = true;
        for (Edge e : G.adj(v)) {
            if (!marked[e.other(v)]) {
                pq.insert(e);
            }
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }
}
