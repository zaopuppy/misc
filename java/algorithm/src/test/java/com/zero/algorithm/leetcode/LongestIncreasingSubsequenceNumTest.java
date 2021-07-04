package com.zero.algorithm.leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LongestIncreasingSubsequenceNumTest {
    @Test
    void test001() {
        LongestIncreasingSubsequenceNum o = new LongestIncreasingSubsequenceNum();
        assertEquals(2, o.findNumberOfLIS(new int[] {1,3,5,4,7}));
    }

    @Test
    void test002() {
        LongestIncreasingSubsequenceNum o = new LongestIncreasingSubsequenceNum();
        assertEquals(5, o.findNumberOfLIS(new int[] {2, 2, 2, 2, 2}));
    }

    @Test
    void myTest001() {
        LongestIncreasingSubsequenceNum o = new LongestIncreasingSubsequenceNum();
        assertEquals(0, o.findNumberOfLIS(new int[] {}));
        assertEquals(1, o.findNumberOfLIS(new int[] {5}));
    }
}