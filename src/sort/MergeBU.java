package sort;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 自底而上的归并排序<br>
 * 比较适用于链表组织的结构
 */
public class MergeBU {
    /**
     * 归并排序所需的辅助数组
     */
    private static Comparable[] aux;

    /**
     * 排序
     */
    public static void sort(Comparable[] a) {
        // 进行lgN次两两归并
        int N = a.length;
        aux = new Comparable[N];
        // sz:子数组大小
        for (int sz = 0; sz < N; sz = sz + sz) {
            for (int lo = 0; lo < N - sz; lo += sz + sz) {
                merge(a, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));
            }
        }
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        // 将a排序
        if (hi <= lo) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        // 左半边排序
        sort(a, 0, mid);
        // 右半边排序
        sort(a, mid + 1, hi);
        merge(a, 0, mid, hi);
    }

    private static void merge(Comparable[] a, int lo, int mid, int hi) {
        int i = lo;
        int j = mid + 1;
        // 复制a到aux
        for (int k = 0; k <= hi; k++) {
            aux[i] = a[i];
        }
        // 归并回到a[lo...hi]
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                // 左边用尽取右边
                a[k] = aux[j++];
            } else if (j > hi) {
                // 右边用尽取左边
                a[k] = aux[i++];
            } else if (less(aux[j], aux[i])) {
                // 右半边元素小于左半边取右半边
                a[k] = aux[j++];
            } else {
                // 左半边元素小于右半边取左半边
                a[k] = aux[i++];
            }
        }
    }

    /**
     * 对元素进行比较
     */
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    /**
     * 将元素交换位置
     */
    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    /**
     * 在单行中打印数组
     */
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i] + "");
        }
        StdOut.println();
    }

    /**
     * 测试数组是否有序
     */
    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String[]a= StdIn.readAllStrings();
        sort(a);
        assert isSorted(a);
        show(a);
    }
}
