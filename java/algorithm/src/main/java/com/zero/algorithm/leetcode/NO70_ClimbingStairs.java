package com.zero.algorithm.leetcode;


/**
 * https://leetcode-cn.com/problems/climbing-stairs/
 */
public class NO70_ClimbingStairs {

    /**
     * f(0) = 0
     * f(1) = 1
     * f(2) = 2 = f(1) + 1
     * f(3) = f(2) + 1 = 3
     * f(n) = f(n-1) + 1 -- which is wrong
     *
     * there are two ways:
     *   a) step 1, then do the left
     *   b) step 2, then do the left
     *
     * so the formula is:
     *   f(n) = f(n-1) + f(n-2)    ( 2 <= n)
     *
     * but!! there's a special condition: f(0), which means no stairs left,
     * but still count as 1, so the formula should be
     *
     *   f(n) = max(f(n-1), 1) + max(f(n-2), 1)
     *
     * or simpler as below, to avoid special case
     *
     *   f(n) = f(n-1) + f(n-2)    (3 <= n)
     *
     *
     * @param n input
     * @return number of stairs
     */
    public int climbStairs(int n) {
        if (n <= 0) return 0;
        if (n <= 1) return 1;
        if (n <= 2) return 2;

        int result = 0;
        int last = 2;
        int lastOfLast = 1;

        for (int i = 3; i <= n; ++i) {
            result = last + lastOfLast;
            lastOfLast = last;
            last = result;
        }

        return result;
    }
}
