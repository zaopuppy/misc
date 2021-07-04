package com.zero.algorithm.leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LongestIncreasingSubsequenceTest {
    @Test
    void test00() {
        final LongestIncreasingSubsequence o = new LongestIncreasingSubsequence();
        assertEquals(4, o.lengthOfLIS(new int[] {10,9,2,5,3,7,101,18}));
    }

    @Test
    void test01() {
        final LongestIncreasingSubsequence o = new LongestIncreasingSubsequence();
        assertEquals(3, o.lengthOfLIS(new int[] {4,10,4,3,8,9}));
    }

    @Test
    void test02() {
        final LongestIncreasingSubsequence o = new LongestIncreasingSubsequence();
        assertEquals(6, o.lengthOfLIS(new int[] {1,3,6,7,9,4,10,5,6}));
    }



    @Test
    void myTest01() {
        final LongestIncreasingSubsequence o = new LongestIncreasingSubsequence();
        assertEquals(1, o.lengthOfLIS(new int[] {10}));
    }

    @Test
    void performance() {

    }
}