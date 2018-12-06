package graph;

/**
 * 使用深度优先搜索找出图中的所有连通分量<br></br>
 * 和union-find算法的对比：<br></br>
 * <li>1.理论上基于深度优先会快于union-find，然而实际趋势相反，因为union-find不需要完整地构建一幅图</li>
 * <li>2.union-find是一种动态算法，深度优先搜索则必须要对图进行预处理</li>
 * <li>3.如果只需要判断连通性或者需要完成有大量连通性查询和插入操作混合等类似任务，更倾向于使用union-find；
 * 而深度优先搜索则更适合实现图的抽象数据类型，因为它能更有效地利用已有的数据类型</li>
 * ---连通性问题
 */
public class CC {
    private boolean[] marked;
    private int[] id;
    private int count;

    public CC(Graph G) {
        marked = new boolean[G.V()];
        for (int s = 0; s < G.V(); s++) {
            if (!marked[s]) {
                dfs(G, s);
                count++;
            }
        }
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    public boolean connected(int v, int w) {
        // 也就是判断是否属于同一个连通分量
        return id[v] == id[w];
    }

    public int id(int v) {
        return id[v];
    }

    public int count() {
        return count;
    }
}
