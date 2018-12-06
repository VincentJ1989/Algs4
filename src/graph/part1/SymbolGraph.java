package graph.part1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;

/**
 * 符号图<p>
 * 图的经典问题--间隔的度数：查找一个社交网络中2个人间隔的度数
 */
public class SymbolGraph {
    /**符号名-->索引*/
    private ST<String, Integer> st;
    /**索引-->符号名*/
    private String[] keys;
    /**图*/
    private Graph G;

    public SymbolGraph(String stream, String sp) {
        st = new ST<>();
        // 第一遍构造索引
        In in = new In(stream);
        while (in.hasNextLine()) {
            // 读取字符串
            String[] a = in.readLine().split(sp);
            // 为每个不同的字符串关联一个索引
            for (int i = 0; i < a.length; i++) {
                if (!st.contains(a[i])) {
                    st.put(a[i], st.size());
                }
            }
        }
        // 用来获取顶点名的反向索引是一个数组
        keys = new String[st.size()];
        for (String name : st.keys()) {
            keys[st.get(name)] = name;
        }

        // 第二遍构造图
        G = new Graph(st.size());
        in = new In(stream);
        while (in.hasNextLine()) {
            // 将每一行的第一个顶点和该行的其他顶点相连
            String[] a = in.readLine().split(sp);
            int v = st.get(a[0]);
            for (int i = 1; i < a.length; i++) {
                G.addEdge(i, st.get(a[i]));
            }
        }
    }

    public boolean contains(String s) {
        return st.contains(s);
    }

    public int index(String s) {
        return st.get(s);

    }

    public String name(int v) {
        return keys[v];
    }

    public Graph G() {
        return G;
    }

}
