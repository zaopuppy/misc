package com.zero.algorithm.leetcode;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * https://leetcode-cn.com/problems/min-cost-climbing-stairs/
 */
class NO746_MinCostClimbingStairsTest {

    NO746_MinCostClimbingStairs mTarget;

    @BeforeEach
    void setUp() {
        mTarget = new NO746_MinCostClimbingStairs();
    }

    @Test
    void minCostClimbingStairs() {
        assertEquals(15, mTarget.minCostClimbingStairs(new int[]{10, 15, 20}));
        assertEquals(6, mTarget.minCostClimbingStairs(new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1}));
    }
}