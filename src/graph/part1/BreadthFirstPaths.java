package graph.part1;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

/**
 * 使用广度优先搜素查找图中的路径<p>
 * 使用一个队列来保存所有被标记过但起邻接表还未被检查过的顶点--单点最短路径问题
 */
public class BreadthFirstPaths {
    /**起点*/
    private final int s;
    /**到达该顶点的最短路径已知吗?*/
    private boolean[] marked;
    /**到达该顶点的已知路径上的最后一个顶点*/
    private int[] edgTo;

    public BreadthFirstPaths(Graph G, int S) {
        marked = new boolean[G.V()];
        edgTo = new int[G.V()];
        this.s = S;
        dfs(G, s);
    }

    private void dfs(Graph G, int s) {
        Queue<Integer> queue = new Queue<Integer>();
        // 标记起点
        marked[s] = true;
        // 将它加入队列
        queue.enqueue(s);
        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            // 对于每个未被标记的相邻顶点
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    // 保存最短路径的最后一条边
                    edgTo[w] = v;
                    // 标记它，因为最短路径已知
                    marked[w] = true;
                    // 并将它添加到队列
                    queue.enqueue(w);
                }
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
