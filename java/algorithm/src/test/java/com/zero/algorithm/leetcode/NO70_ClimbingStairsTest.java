package com.zero.algorithm.leetcode;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NO70_ClimbingStairsTest {

    NO70_ClimbingStairs mTarget;

    @BeforeEach
    void setUp() {
        mTarget = new NO70_ClimbingStairs();
    }

    @Test
    void climbStairs() {
        assertEquals(0, mTarget.climbStairs(0));
        assertEquals(1, mTarget.climbStairs(1));
        assertEquals(2, mTarget.climbStairs(2));
        assertEquals(3, mTarget.climbStairs(3));
        // . . . . .
        // | |     |
        // |   |   |
        assertEquals(5, mTarget.climbStairs(4));
        // . . . . . .
        // | |       |
        // |   |     |
        assertEquals(8, mTarget.climbStairs(5));
    }
}