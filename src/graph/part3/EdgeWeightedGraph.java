package graph.part3;

import edu.princeton.cs.algs4.Bag;

/**
 * 加权无向图的数据类型<br></br>
 * 允许平行边和自环
 */
public class EdgeWeightedGraph {
    /**顶点总数*/
    private final int V;
    /**边的总数*/
    private int E;
    /**邻接表*/
    private Bag<Edge>[] adj;

    public EdgeWeightedGraph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<Edge>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<>();
        }
    }

    // public EdgeWeightedGraph(In in, int v) {
    // // TODO: 2018-12-10 练习4.3.9
    // }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(Edge e) {
        int v = e.either();
        int w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
    }

    public Iterable<Edge> adj(int v) {
        return adj[v];
    }

    public Iterable<Edge> edges() {
        Bag<Edge> b = new Bag<>();
        for (int v = 0; v < V; v++) {
            for (Edge e : adj(v)) {
                if (e.other(v) > v) {
                    b.add(e);
                }
            }
        }
        return b;
    }
}
