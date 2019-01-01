package string.stringsearch;

import java.math.BigInteger;
import java.util.Random;

/**
 * Rabin-Karp指纹字符串查找算法(一种基于散列的字符串查找算法)
 */
public class RabinKarp {
    /**模式字符串(仅拉斯维加斯算法需要)*/
    private String pat;
    /**模式字符串的散列表*/
    private long patHash;
    /**模式字符串的长度*/
    private int M;
    /**一个很大的素数*/
    private long Q;
    /**字母表的大小*/
    private int R = 256;
    /**R^(M-1)%Q的余数*/
    private long RM;

    public RabinKarp(String pat) {
        // 保存模式字符串(仅拉斯维加斯算法需要)
        this.pat = pat;

        this.M = pat.length();
        Q = longRandomPrime();
        RM = 1;
        // 计算R^(M-1)%Q
        for (int i = 1; i <= M - 1; i++) {
            // 用于减去第一个数字时的计算
            RM = (R * RM) % Q;
        }
        patHash = hash(pat, M);
    }

    private long hash(String key, int M) {
        // 计算key[0..M-1]的散列值
        long h = 0;
        for (int j = 0; j < M; j++) {
            h = (R * h) + key.charAt(j) % Q;
        }
        return h;
    }

    private long longRandomPrime() {
        BigInteger prime = BigInteger.probablePrime(31, new Random());
        return prime.longValue();
    }

    private int search(String txt) {
        // 在文本中查找相等的散列值
        int N = txt.length();
        long txtHash = hash(txt, M);
        // 一开始就匹配成功
        if (patHash == txtHash && check(txt, 0)) {
            return 0;
        }
        for (int i = M; i < N; i++) {
            // 减去第一个数字，加上最后一个数字，再次检查匹配
            txtHash = (txtHash + Q - RM * txt.charAt(i - M) % Q) % Q;
            txtHash = (txtHash * R + txt.charAt(i)) % Q;
            if (patHash == txtHash) {
                if (check(txt, i - M + 1)) {
                    // 找到匹配
                    return i - M + 1;
                }
            }
        }
        // 未找到匹配
        return N;
    }

    // Monte Carlo version: always return true
    // private boolean check(int i) {
    // return true;
    // }

    private boolean check(String txt, int i) {
        //对于拉斯维加斯算法，检查模式与txt(i..i-M+1)的匹配
        for (int j = 0; j < M; j++) {
            if (pat.charAt(j) != txt.charAt(i + j)) {
                return false;
            }
        }
        return true;
    }
}
