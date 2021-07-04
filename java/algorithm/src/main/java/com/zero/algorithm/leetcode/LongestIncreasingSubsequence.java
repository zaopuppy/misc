package com.zero.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/longest-increasing-subsequence/
 *
 * dp[i] is the LIS which ends with a[i]
 *
 * dp[i] = if (a[i] > a[i-1]) { dp[i-1] + 1 } else { 1 }
 *
 * dp[i] =
 *
 */
public class LongestIncreasingSubsequence {
    private final Map<Integer, Integer> cache = new HashMap<>();

    private int finalMax = 0;

    public int lengthOfLIS(int[] nums) {
        int max = foo(nums, nums.length - 1);
        return Math.max(finalMax, max);
    }

    private int foo(int[] nums, int pos) {
        if (pos < 0) { return 0; }
        if (pos <= 0) { return 1; }
        if (cache.containsKey(pos)) { return cache.get(pos); }

        int max = 1;
        for (int i = 0; i < pos; ++i) {
            int subMax = foo(nums, i);
            finalMax = Math.max(subMax, finalMax);
            if (nums[i] < nums[pos]) {
                max = Math.max(subMax+1, max);
            }
        }

        cache.put(pos, max);
        return max;
    }
}
