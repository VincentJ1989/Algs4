package string.stringsearch;

/**
 * Boyer-Moore字符串匹配算法(启发式地处理不匹配的字符)
 */
public class BoyerMoore {
    private int[] right;
    private String pat;

    public BoyerMoore(String pat) {
        // 计算跳跃表
        this.pat = pat;
        int M = pat.length();
        int R = 256;
        right = new int[R];

        for (int c = 0; c < R; c++) {
            // 不包含在模式字符串中的字符的值为-1
            right[c] = -1;
        }

        for (int j = 0; j < M; j++) {
            // 包含在模式字符串中的字符的值为它在其中出现的最右的位置
            right[pat.charAt(j)] = j;
        }
    }

    public int search(String txt) {
        // 在txt中查找模式字符串
        int N = txt.length();
        int M = pat.length();
        int skip;
        for (int i = 0; i <= N - M; i += skip) {
            // 模式字符串和文本在位置i匹配吗?
            skip = 0;
            for (int j = M - 1; j >= 0; j--) {
                skip = j - right[txt.charAt(i + j)];
                if (skip < 1) {
                    skip = 1;
                }
                break;
            }
            if (skip == 0) {
                // 找到匹配
                return i;
            }
        }
        // 未找到匹配
        return N;
    }
}
