package com.zero.algorithm.leetcode;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class MaximumSubarrayTest {
    @Test
    void sample00() {
        final MaximumSubarray o = new MaximumSubarray();
        assertEquals(6, o.maxSubArray(new int[] {-2,1,-3,4,-1,2,1,-5,4}));
    }

    @Test
    void performance00() {
        int size = 9999;
        int[] bigArray = new int[size];
        {
            Random random = new Random();
            for (int i = 0; i < size; ++i) {
                bigArray[i] = 5 - random.nextInt(10);
            }
        }

        MaximumSubarray o = new MaximumSubarray();

        long begin = System.currentTimeMillis();
        o.maxSubArray(bigArray);
        long delta = System.currentTimeMillis() - begin;
        System.out.println(delta);
        assertTrue(delta < 1000);
    }
}