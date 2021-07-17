package com.zero.algorithm.leetcode;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NO1035_UncrossedLinesTest {

    private NO1035_UncrossedLines mTarget;

    @BeforeEach
    void setUp() {
        mTarget = new NO1035_UncrossedLines();
    }

    @Test
    void maxUncrossedLines00() {
        int act;
        act = mTarget.maxUncrossedLines(
                new int[] { 1, 4, 2}, new int[] { 1, 2, 4 }
        );
        assertEquals(2, act);

        act = mTarget.maxUncrossedLines(
                new int[] { 2, 5, 1, 2, 5 }, new int[] { 10, 5, 2, 1, 5, 2 }
        );
        assertEquals(3, act);

        act = mTarget.maxUncrossedLines(
                new int[] { 1, 3, 7, 1, 7, 5 }, new int[] { 1, 9, 2, 5, 1 }
        );
        assertEquals(2, act);
    }
}