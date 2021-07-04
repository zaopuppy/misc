package com.zero.algorithm.leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LongestCommonSequenceTest {
    @Test
    public void test00() {
        LongestCommonSequence o = new LongestCommonSequence();
        assertEquals(3, o.longestCommonSubsequence("abcde", "ace"));
    }
}