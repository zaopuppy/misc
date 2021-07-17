package com.zero.algorithm.leetcode;


/**
 * https://leetcode-cn.com/problems/maximum-subarray/
 *
 * assume array[i, j] is the maximum sub-array of array, then
 * what kind of characteristics does it have?
 *
 * 1) array[j] > 0
 * 2) sum(array[i, j-1]) > 0
 *
 * assume the maximum sun of sub-array of a[i, j] is dp[i, j]
 *
 * dp[i, j] = max(
 *     dp[i-1, j] + (a[i] if a[i] >= 0),
 *     dp[i, j-1] + (a[j] if a[j] >= 0));
 *
 * dp[0, i] = max(dp[0, i-1] + a[i], a[i])
 *
 * 4, 0, -1, -2, 100
 *
 *
 */
public class MaximumSubarray {
    public int maxSubArray(int[] nums) {
        int max = nums[0];
        int dp = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            int n = nums[i];
            dp = Math.max(dp + n, n);
            max = Math.max(dp, max);
        }
        return max;
    }
}
