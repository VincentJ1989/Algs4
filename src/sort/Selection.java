package sort;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 选择排序<br></br>
 * 1、运行时间和输入无关、数据移动是最少的
 * 2、N次交换+(N2)/2次比较
 */
public class Selection {
    /**
     * 排序
     */
    public static void sort(Comparable[] a) {
        // 将a按升序排列
        // 数组长度
        int N = a.length;
        for (int i = 0; i < N; i++) {
            // 将a[i]与a[i+1...N]中的最小元素交换
            // 最小元素的索引
            int min = i;
            for (int j = i + 1; j < N; j++) {
                if (less(a[j], a[i])) {
                    min = j;
                }
            }
            exch(a, i, min);
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
