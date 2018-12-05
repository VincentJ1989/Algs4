package graph;

import java.util.Stack;

/**
 * 基于深度优先搜索实现的Path
 */
public class DepthFirstPaths {
    /**起点*/
    private final int s;
    /**这个顶点调用过dfs()了吗?*/
    private boolean[] marked;
    /**从起点到一个顶点的已知路径上的最后一个顶点*/
    private int[] edgTo;

    public DepthFirstPaths(Graph G, int s) {
        marked = new boolean[G.V()];
        edgTo = new int[G.V()];
        this.s = s;
        dfs(G, s);
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                edgTo[w] = v;
                dfs(G, w);
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<Integer> path = new Stack<>();
        for (int x = v; x != s; x = edgTo[x]) {
            path.push(x);
        }
        path.push(s);
        return path;
    }
}
