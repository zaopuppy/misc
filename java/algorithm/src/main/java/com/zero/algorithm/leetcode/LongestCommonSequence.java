package com.zero.algorithm.leetcode;


import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/longest-common-subsequence/
 *
 * 虽然是一道很经典的题目, 我也能记得标准答案, 但是, 我觉得这个"标准答案",
 * 不应该仅仅是一个, 因为它直接告诉了我, 使用什么方式去描述问题的递推(),
 * 却并没有告诉我其中的思考过程
 *
 * 为什么这么说, 因为正常人是很难直接一步到位, 直接得到这种答案的, 因为过于反直觉
 *
 * 更像是一个完整的推理之后, 再做大量简化得到的答案, 于是我们将无法收获其中的思考
 *
 * 更难自行去思考类似的问题
 *
 * 可以拆分子问题的前提是:
 *   源子串s1, 源子串s2, 最大子串s3
 *   最大子串s3删除最后一个字符c, 得到s3'
 *   s1和s2都删除掉最大子串的最后一个字符c, 得到s1'和s2'
 *   s3'依然是s1'和s2'的最大子串
 *
 * 1a3b4d5ef
 * 13ab4d5ef
 * 1345
 * abdef
 *
 * s1, s2 -> s3
 *
 * s3  = "ace",  [(2,1), (5,3), (9,4)], (9, 4)
 * s3' = (10, 5)
 * for (9+1, ...), (4+1, ...)
 *   if (c1 == c2) s3' += 1; break;
 *
 * 这种思考模式, 会导致s3'的计算非常困难, 比如
 *
 * s1 = "123++++++++"
 * s2 = "123-------------"
 *
 * '3'之后有很大一个长度不相等, 每次字符串的增加, 都需要对之前不匹配的长度做一次匹配
 *
 * 如何优化掉这个耗时比较呢
 *
 * 先考虑这个耗时到底是否可能被优化?
 *
 * 似乎不可能?
 *
 */
public class LongestCommonSequence {
    public int longestCommonSubsequence(String s1, String s2) {
        InnerInfo s3 = new InnerInfo();
        s3.lastPos = new Pair(s1.length()-1, -1);

        for (int i = 0; i < s2.length(); ++i) {
            char c = s2.charAt(i);
            for (int j = s3.lastPos.first+1; j < s1.length(); ++j) {

            }
        }

        return s3.s.length();
    }

    static class Pair {
        int first = -1;
        int second = -1;

        Pair(int fst, int sec) {
            first = fst;
            second = sec;
        }
    }
    static class InnerInfo {
        String s = "";
        final List<Pair> matchPosList = new ArrayList<>();
        Pair lastPos = new Pair(-1, -1);
    }
}
