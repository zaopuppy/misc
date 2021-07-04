package com.zero.algorithm.leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RussiaTaowaTest {
    @Test
    void test001() {
        RussiaTaowa o = new RussiaTaowa();
        assertEquals(3, o.maxEnvelopes(new int[][] {{5,4},{6,4},{6,7},{2,3}}));
    }

    @Test
    void test002() {
    }

    @Test
    void myTest001() {
        RussiaTaowa o = new RussiaTaowa();
        assertEquals(0, o.maxEnvelopes(new int[][] {}));
        assertEquals(1, o.maxEnvelopes(new int[][] {{100, 200}}));
        assertEquals(2, o.maxEnvelopes(new int[][] {{1,2}, {2,3}}));
        assertEquals(2, o.maxEnvelopes(new int[][] {{1,2}, {2,3}}));
        assertEquals(3, o.maxEnvelopes(new int[][] {{1,2}, {2,8}, {3,3}, {7,8}}));
    }

}