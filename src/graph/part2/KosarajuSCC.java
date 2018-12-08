package graph.part2;

/**
 * Kosaraju算法--计算强连通分量
 */
public class KosarajuSCC {
    /**已访问过的顶点*/
    private boolean[] marked;
    /**强连通分量的标识符*/
    private int[] id;
    /**强连通分量的数量*/
    private int count;

    public KosarajuSCC(Digraph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        // ①计算反向图
        DepthFirstOrder order = new DepthFirstOrder(G.reverse());
        // ②按照计算得到的顺序进行标准的深度优先搜索
        for (int s : order.reversePost()) {
            if (!marked[s]) {
                dfs(G, s);
                count++;
            }
        }

    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    public boolean stronglyConnected(int v, int w) {
        return id[v] == id[w];

    }

    public int id(int v) {
        return id[v];
    }

    public int count() {
        return count;
    }
}
