package com.zero.algorithm.leetcode;

/**
 * https://leetcode-cn.com/problems/fibonacci-number/
 */
public class NO509_Fibonacci {
    public int fib(int n) {
        if (n <= 0) return 0;
        if (n <= 1) return 1;

        int last = 1;
        int lastOfLast = 0;
        int result = 1;
        for (int i = 2; i <= n; ++i) {
            result = last + lastOfLast;
            lastOfLast = last;
            last = result;
        }
        return result;
    }

    /**
     * legacy method
     *
     * f(n) = f(n-1) + f(n-2)
     *
     */
    public int fibOld(int n) {
        if (n <= 0) return 0;
        if (n <= 1) return 1;
        return fibOld(n - 1) + fibOld(n - 2);
    }
}
