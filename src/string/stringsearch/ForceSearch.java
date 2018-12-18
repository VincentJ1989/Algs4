package string.stringsearch;

/**
 * 暴力子字符串查找算法
 */
public class ForceSearch {
    /**
     * 暴力查找第一版本
     * @param pat 模式--也就是需要被查找的子字符串
     * @param txt 文本内容
     */
    public static int search(String pat, String txt) {
        int M = pat.length();
        int N = txt.length();

        for (int i = 0; i < N - M; i++) {
            int j = 0;
            for (; j < M; j++) {
                if (txt.charAt(i + j) != pat.charAt(j)) {
                    break;
                }
            }
            if (j == M) {
                // 找到匹配
                return i;
            }
        }
        // 未找到匹配
        return N;
    }

    /**
     * 暴力查找第2版(显示回退)
     */
    public static int optSearch(String pat, String txt) {
        int M = pat.length();
        int N = txt.length();
        int i;
        int j;
        for (i = 0, j = 0; i < N && j < M; i++) {
            if (txt.charAt(i) == pat.charAt(j)) {
                j++;
            } else {
                i -= j;
                j = 0;
            }
        }
        if (j == M) {
            // 找到匹配
            return i - M;
        } else {
            // 未找到匹配
            return N;
        }
    }

}
