package sort;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 排序算法类的模板
 */
public class Example {
    /**
     * 排序
     */
    public static void sort(Comparable[] a) {
        // TODO: 2018/11/13 这里是具体的实现
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
