package sort;

/**
 * 基于堆的优先队列
 */
public class MaxPQ<Key extends Comparable<Key>> {
    /**
     * 基于堆的完全二叉树
     */
    private Key[] pq;
    /**
     * 存储于pq[1..N]中，pq[0]不使用
     */
    private int N = 0;

    public MaxPQ(int maxN) {
        pq = (Key[]) new Comparable[maxN + 1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void insert(Key v) {
        pq[++N] = v;
        swim(N);
    }

    public Key delMax() {
        Key max = pq[1];
        exch(1, N--);
        pq[N + 1] = null;
        sink(1);
        return max;
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    public void exch(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    /**
     * 上浮
     * @param k
     */
    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k / 2, k);
            k = k / 2;
        }
    }

    /**
     * 下沉
     * @param k
     */
    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(j, j + 1)) {
                j++;
            }
            if (!less(k, j)) {
                break;
            }
            exch(k, j);
            k = j;
        }
    }

//    /**
//     * 堆排序
//     * @param a
//     */
//    public void sort(Comparable[] a) {
//        int N = a.length;
//        for (int k = N / 2; k >= 1; k--) {
//            sink(a, k, N);
//            while (N > 1) {
//                exch(a, 1, N--);
//                sink(a, 1, N);
//            }
//        }
//    }

}
