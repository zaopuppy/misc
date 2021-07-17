package com.zero.algorithm.leetcode;


/**
 * https://leetcode-cn.com/problems/wiggle-subsequence/
 */
public class NO376_WiggleSubsequence {

    /**
     * j > i: positive
     * j < i: negative
     * positive(i, j) = f(i, -1) + 1 if nums[j] > nums[i] else 1
     * negative(i, j) = f(i) + 1 if nums[j] < nums[i] else 1
     *
     * f(0, -1) = 0
     * f(0,  1) = 0
     * f(1, -1) = max(  f(0, -1), f(i, 1)+1  )
     * f(1, -1) = 1
     *
     * f(i, -1) = max(f(x,  1)+1 if nums[i]-nums[x] < 0 else 1)  | x=[0, i-1]
     * f(i,  1) = max(f(x, -1)+1, 1)  | x=[0, i-1]
     *
     * f(i+1, -1) =       max(f(x, 1)+1, 1) | x[0, i]
     *            =       max(f(x, 1)+1, f(i, 1)+1, 1  ) | x[0, i-1]
     *            = max(  max(f(x, 1)+1), f(i, 1)+1, 1  ) | x[0, i-1]
     *            = max(  f(i, -1), f(i, 1)+1  )
     *
     * dp(i) = max(dp(i-1), f(i)
     *
     */
    public int wiggleMaxLength(int[] nums) {
        return -1;
    }
}
