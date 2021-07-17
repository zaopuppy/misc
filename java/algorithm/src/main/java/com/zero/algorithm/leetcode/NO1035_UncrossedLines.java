package com.zero.algorithm.leetcode;


/**
 * https://leetcode-cn.com/problems/wiggle-subsequence/
 */
public class NO1035_UncrossedLines {

    /**
     * f(i, j) = f(i-1, j-1) + 1 if nums1[i] == nums2[j] else
     *           max(f(i-1, j), f(i, j-1))
     */
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length;
        int[] fi = new int[n2];

        for (int i = 0; i < n1; ++i) {
            // f(i-1, j-1)
            int last = fi[0];
            fi[0] = nums1[i] == nums2[0] ? 1 : last;
            for (int j = 1; j < n2; ++j) {
                int tmp = fi[j];
                fi[j] = nums1[i] == nums2[j] ? last + 1 : Math.max(fi[j], fi[j-1]);
                last = tmp;
            }
        }

        return fi[n2-1];
    }
}
