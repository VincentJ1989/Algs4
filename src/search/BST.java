package search;

import edu.princeton.cs.algs4.Queue;

/**
 * 二叉查找树
 */
public class BST<Key extends Comparable<Key>, Value> {
    // 二叉查找树的根节点
    private Node root;

    private class Node {
        // 键
        private Key key;
        // 值
        private Value val;
        // 指向子树额链接
        private Node left, right;
        // 以该结点为根的子树中的节点总数
        private int N;

        public Node(Key key, Value val, int n) {
            this.key = key;
            this.val = val;
            N = n;
        }

        public int size() {
            return size(root);
        }

        private int size(Node x) {
            if (x == null) {
                return 0;
            } else {
                return N;
            }
        }

        public Value get(Key key) {
            return get(root, key);
        }

        private Value get(Node x, Key key) {
            // 在以x为根结点的子树中查找并返回key所对应的值，如果找不到则返回null
            if (x == null) {
                return null;
            }

            int cmp = key.compareTo(x.key);

            if (cmp < 0) {
                return get(x.left, key);
            } else if (cmp > 0) {
                return get(x.right, key);
            } else {
                return x.val;
            }
        }

        public void put(Key key, Value val) {
            // 查找key，找到则更新；反之则创建新的结点
            root = put(root, key, val);
        }

        private Node put(Node x, Key key, Value val) {
            // 如果key存在于以x为根结点的子树中则更新它的值，否则将以key和val为键值的新结点插入到该子树中
            if (x == null) {
                return new Node(key, val, 1);
            }
            int cmp = key.compareTo(x.key);
            if (cmp < 0) {
                x.left = put(x.left, key, val);
            } else if (cmp > 0) {
                x.right = put(x.right, key, val);
            } else {
                x.val = val;
            }
            x.N = size(x.left) + size(x.right) + 1;
            return x;
        }

        public Node min() {
            return min(root);
        }

        private Node min(Node x) {
            if (x == null) {
                return null;
            }
            return min(x.left);
        }

        public Value max() {
            return max(root);
        }

        private Value max(Node x) {
            if (x == null) {
                return null;
            }
            return max(x.right);
        }

        public int rank(Key key) {
            return rank(key, root);
        }

        private int rank(Key key, Node x) {
            // 返回以x为根结点的子树中小鱼x.key的键的数量
            if (x == null) {
                return 0;
            }
            int cmp = key.compareTo(x.key);
            if (cmp < 0) {
                return rank(key, x.left);
            } else if (cmp > 0) {
                return size(x.left) + rank(key, x.right) + 1;
            } else {
                return size(x);
            }
        }

        public void deleteMin() {
            deleteMin(root);
        }

        private Node deleteMin(Node x) {
            if (x.left == null) {
                return x.right;
            }
            x.left = deleteMin(x.left);
            x.N = size(x.left) + size(x.right) + 1;
            return x;
        }

        public void delete(Key key) {
            root = delete(root, key);
        }

        private Node delete(Node x, Key key) {
            if (x == null) {
                return null;
            }
            int cmp = key.compareTo(x.key);
            if (cmp < 0) {
                return x.left = delete(x.left, key);
            } else if (cmp > 0) {
                return x.right = delete(x.right, key);
            } else {

                if (x.right == null) {
                    return x.left;
                }
                if (x.left == null) {
                    return x.right;
                }
                /*
                 * 1.将指向即将被删除的结点的链接保存为t<br></br>
                 * 2.将x指向它的后继结点min(t.right)<br></br>
                 * 3.将x的右链接指向deleteMin(t.right),也就是在删除后所有结点仍然都大于x.key的子二叉查找树<br></br>
                 * 4.将x的左链接(本为空)设为t.left（其下所有的键都小于被删除的结点和它的后继结点）
                 */
                Node t = x;
                x = min(t.right);
                x.right = deleteMin(t.right);
                x.left = t.right;
            }

            x.N = size(x.left) + size(x.right) + 1;

            return x;
        }

        public Iterable<Key> keys() {
            return keys();
        }

        public Iterable<Key> keys(Key lo, Key hi) {
            Queue<Key> queue = new Queue<Key>();
            keys(root, queue, lo, hi);
            return queue;
        }

        private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
            if (x == null) {
                return;
            }
            // 中序遍历--先输出左，再输出右
            int cmplo = lo.compareTo(x.key);
            int cmphi = hi.compareTo(x.key);
            if (cmplo < 0) {
                keys(x.left, queue, lo, hi);
            }
            if (cmplo <= 0 && cmphi >= 0) {
                queue.enqueue(x.key);
            }
            if (cmphi > 0) {
                keys(x.right, queue, lo, hi);
            }
        }
    }
}
