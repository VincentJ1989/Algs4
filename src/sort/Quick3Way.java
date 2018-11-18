package sort;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 快速排序
 */
public class Quick3Way {
    /**
     * 排序
     */
    public static void sort(Comparable[] a) {
        // 消除对输入的依赖
        StdRandom.shuffle(a);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi < lo) {
            return;
        }
        // 切分
        int j = partiton(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j, hi);
    }

    /**
     * 切分<br></br>
     * 实现的内容为:<p>
     * <li>1.对于某个j，a[j]已经排定</li>
     * <li>2.a[lo]到a[j-1]中的元素都不大于a[j]</li>
     * <li>3.a[j+1]到a[hi]中的元素都不小于a[j]</li>
     */
    private static int partiton(Comparable[] a, int lo, int hi) {
        // 将数组分为3部分
        // 左右扫描指针
        int i = lo;
        int j = hi + 1;
        Comparable v = a[lo];
        for (;;) {
            // 扫描左右，检查扫描是否结束并交换元素
            while (less(a[++i], v)) {
                if (i == hi) {
                    break;
                }
            }

            while (less(v, a[--j])) {
                if (j == lo) {
                    break;
                }
            }

            if (i >= j) {
                break;
            }
            exch(a, i, j);
        }
        // 将v=a[j]放入正确的位置
        exch(a, lo, j);
        return j;
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
