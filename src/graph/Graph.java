package graph;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

/**
 * Graph数据类型
 */
public class Graph {
    /**顶点数目*/
    private final int V;
    /**边的数目*/
    private int E;
    /**邻接表*/
    private Bag<Integer>[] adj;

    public Graph(int V) {
        this.V = V;
        this.E = 0;
        // 创建邻接表
        adj = (Bag<Integer>[]) new Bag[V];
        // 将所有链表初始化为空
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Integer>();

        }
    }

    public Graph(In in) {
        // 读取V并将图初始化
        this(in.readInt());
        // 读取E
        int E = in.readInt();
        for (int i = 0; i < E; i++) {
            // 添加一条边
            // 读取一个顶点
            int v = in.readInt();
            // 读取另一个顶点
            int w = in.readInt();
            // 添加一条连接它们的边
            addEdge(v, w);
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(int v, int w) {
        // 将w添加到v的链表中
        adj[v].add(w);
        // 将v添加到w的链表中
        adj[w].add(v);
        E++;
    }

    public Iterable<Integer> adj(int V) {
        return adj(V);
    }
}
