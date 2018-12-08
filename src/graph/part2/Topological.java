package graph.part2;

/**
 * 拓扑排序--当且仅当一幅有向图是无环图时它才能进行拓扑排序。
 */
public class Topological {
    /**顶点的拓扑排序*/
    private Iterable<Integer> order;

    public Topological(Digraph G) {
        DirectedCycle cyclefinder = new DirectedCycle(G);
        // 只有无环图才能排序
        if (!cyclefinder.hasCycle()) {
            DepthFirstOrder dfs = new DepthFirstOrder(G);
            // 使用的是逆后序--一幅DAG的拓扑顺序即为所有顶点的逆后序排列.
            order = dfs.reversePost();
        }
    }

    public Iterable<Integer> order() {
        return order;
    }

    /**
     * 是否是有向无环图
     */
    public boolean isDAG() {
        return order != null;
    }
}
