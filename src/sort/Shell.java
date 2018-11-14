package sort;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 希尔排序
 * @author vincent
 */
public class Shell {
    /**
     * 排序
     */
    public static void sort(Comparable[] a) {
        // 升序排列
        int N = a.length;
        int h = 1;
        while (h < N / 3) {
            // 递增序列：1,4,13,40,121,364,1093...
            h = 3 * h + 1;
        }
        while (h >= 1) {
            // 将数组变为h有序
            for (int i = 0; i < N; i++) {
                // 将a[i]插入到a[i-h],a[1-2*h]...之中
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    exch(a, j, j - h);
                }
            }
            h = h / 3;
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
            StdOut.println();
        }
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
        String[] a = StdIn.readAllStrings();
        sort(a);
        assert isSorted(a);
        show(a);
    }
}
