package graph.part2;

import edu.princeton.cs.algs4.Bag;
import graph.part1.Graph;

/**
 * 有向图Digraph数据类型<br></br>
 * 对比和{@link Graph}的区别
 *
 */
public class Digraph {
    private final int V;
    private int E;
    private Bag<Integer>[] adj;

    public Digraph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<>();
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(int v, int w) {
        // 仅做一次添加，有向性和无向的区别在这
        adj[v].add(w);
        E++;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public Digraph reverse() {
        Digraph R = new Digraph(V);
        for (int v = 0; v < V; v++) {
            for (int w : adj(v)) {
                // 反向添加
                R.addEdge(w, v);
            }
        }
        return R;
    }
}
