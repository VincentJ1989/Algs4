package string.zip;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;
import edu.princeton.cs.algs4.TST;

/**
 * LZW算法的压缩
 */
public class LZW {
    /**输入字符数*/
    private static final int R = 256;
    /**编码总数=2^12*/
    private static final int L = 4095;
    /**编码宽度*/
    private static final int W = 12;

    public static void compress() {
        String input = BinaryStdIn.readString();
        TST<Integer> st = new TST<>();
        for (int i = 0; i < R; i++) {
            st.put("" + (char) i, i);
        }
        // R为文件结束(EOF)的编码
        int code = R + 1;
        while (input.length() > 0) {
            // 找到匹配的最长前缀
            String s = st.longestPrefixOf(input);
            // 打印出s的编码
            BinaryStdOut.write(st.get(s), W);
            int t = s.length();
            if (t < input.length() && code < L) {
                // 将s加入符号表
                st.put(input.substring(0, t + 1), code++);
            }
            // 从输入中读取s
            input = input.substring(t);
        }
        // 写入文件结束的标记
        BinaryStdOut.write(R, W);
        BinaryStdOut.close();
    }

    public static final void expand() {
        String[] st = new String[L];
        // 下一个待补全的编码值
        int i;
        // 用字符初始化编码表
        for (i = 0; i < R; i++) {
            st[i] = "" + (char) i;
        }
        // (未使用)文件结束标记(EOF)的前瞻字符
        st[i++] = " ";

        int codeword = BinaryStdIn.readInt(W);
        String val = st[codeword];

        while (true) {
            // 输出当前子字符串
            BinaryStdOut.write(val);
            codeword = BinaryStdIn.readInt(W);
            if (codeword == R) {
                break;
            }
            // 获取下一个编码
            String s = st[codeword];
            // 如果前瞻字符不可用
            if (i == codeword) {
                // 根据上一个字符串的首字母得到编码的字符串
                s = val + val.charAt(0);
            }
            if (i < L) {
                // 为编译表添加新的条目
                st[i++] = val + s.charAt(0);
            }
            // 更新当前的编码
            val = s;

        }
        BinaryStdOut.close();
    }

}
