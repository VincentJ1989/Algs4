package string.wordesearch;

/**
 * 基于单词查找树的符号表
 */
public class TrieST<Value> {
    /**基数*/
    private static int R = 256;
    /**单词查找树的根节点*/
    private Node root;

    private static class Node {
        private Object val;
        private Node[] next = new Node[R];
    }

    public Value get(String key) {
        Node x = get(root, key, 0);
        if (x == null) {
            return null;
        } else {
            return (Value) x.val;
        }
    }

    private Node get(Node x, String key, int d) {
        // 返回以x作为根节点的子单词查找树中与key相关联的值
        if (x == null) {
            return null;
        }
        if (d == key.length()) {
            return x;
        }
        // 找到第d个字符锁对应的子单词查找树
        char c = key.charAt(d);
        return get(x.next[c], key, d + 1);
    }

    public void put(String key, String val) {
        root = put(root, key, val, 0);
    }

    private Node put(Node x, String key, String val, int d) {
        // 如果key存在于以x为根节点的子单词查找树中，则更新与它想关联的值
        if (x == null) {
            return null;
        }
        if (d == key.length()) {
            x.val = val;
            return x;
        }
        // 找到第d个字符锁对应的子单词查找树
        char c = key.charAt(d);
        x.next[c] = put(x.next[c], key, val, d + 1);
        return x;
    }
}
