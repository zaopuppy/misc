package com.zero.algorithm.leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestTest {

    @Test
    void getMaxMatrix() {
        final com.zero.algorithm.leetcode.Test o = new com.zero.algorithm.leetcode.Test();
        int[] rect = o.getMaxMatrix(new int[][] {
                {-1, 0},
                {0, -1}
        });

        assertArrayEquals(new int[] {0,1,0,1}, o.getMaxMatrix(new int[][]{
                {-1, 0},
                {0, -1}
        }));
        assertArrayEquals(new int[] {0,0,1,1}, o.getMaxMatrix(new int[][]{
                {1, 1},
                {1, 1}
        }));
        assertArrayEquals(new int[] {0,0,0,0}, o.getMaxMatrix(new int[][]{
                {-1, -1},
                {-1, -1}
        }));
        assertArrayEquals(new int[] {0,0,1,2}, o.getMaxMatrix(new int[][]{
                { 1,  1, 1},
                { 1, -1, 1},
                {-1, 1, -1}
        }));
        // [0,0,2,3], [1,1,1,2]
        assertArrayEquals(new int[] {0,0,2,3}, o.getMaxMatrix(new int[][]{
                { 9,-8, 1, 3,-2},
                {-3, 7, 6,-2, 4},
                { 6,-4,-4, 8,-7}
        }));
    }
}