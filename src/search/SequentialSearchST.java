package search;

/**
 * 顺序查找(基于无序链表)<br></br>
 * 适用于小型的问题
 */
public class SequentialSearchST<Key, Value> {
    // 链表首节点
    private Node first;

    private class Node {
        // 链表节点的定义
        Key key;
        Value val;
        Node next;

        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public Value get(Key key) {
        // 查找给定的键，返回相关联的值
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                // 命中
                return x.val;
            }
        }
        // 未命中
        return null;
    }

    public void put(Key key, Value val) {
        // 查找给定的值，找到则更新其值，邹泽在表中新建节点
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                // 命中，更新
                x.val = val;
                return;
            }
        }
        // 未命中，新建节点
        first = new Node(key, val, first);
    }

}
