package graph.part2;

/**
 * 有向图中顶点对的可达性<br></br>
 * 无论对于稀疏还是稠密图，都是较为理想的解决方案，但不适合在实际应用中的大型有向图，
 * 主要是构造函数中所需的控件~V的平方，所需的时间~V（V+E）.
 */
public class TransitiveClosure {
    private DirectedDFS[] all;

    TransitiveClosure(Digraph G) {
        all = new DirectedDFS[G.V()];
        for (int v = 0; v < G.V(); v++) {
            all[v] = new DirectedDFS(G, v);
        }
    }

    public boolean reachable(int v, int w) {
        return all[v] == all[w];
    }
}
