package string.sort;

/**
 * 低位优先的字符串排序<br></br>
 *
 * 1.键索引计数法是一种对于小整数键排序非常有效的方法<br><br/>
 * 2.低位优先的字符串排序算法能够稳定地将定长字符串排序<br></br>
 * 3.使用于键的长度都相同的字符串排序
 */
public class LSD {
    public static void sort(String[] a, int W) {
        // 通过前W个字符将a排序
        int N = a.length;
        int R = 256;
        String[] aux = new String[N];

        for (int d = W - 1; d >= 0; d--) {
            // 根据第d个字符用键索引计数法排序--这也是称之为低位优先的原因
            // ①计算出现的频率（注意+1的用处）
            int[] count = new int[R + 1];
            for (int i = 0; i < N; i++) {
                // 关注这个内部加1的操作的意义
                count[a[i].charAt(d) + 1]++;
            }
            // ②将频率转换成索引
            for (int r = 0; r < R; r++) {
                count[r + 1] += count[r];
            }
            // ③将元素分类
            for (int i = 0; i < N; i++) {
                aux[count[a[i].charAt(d)]++] = a[i];
            }
            // ④回写
            for (int i = 0; i < N; i++) {
                a[i] = aux[i];
            }
        }
    }

}
