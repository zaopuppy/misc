package com.zero.algorithm.leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OneAndZerosTest {

    @Test
    public void test00() {
        OneAndZeros o = new OneAndZeros();
        assertEquals(4, o.findMaxForm(
                new String[]{ "10", "0001", "111001", "1", "0" },
                5, 3
        ));
        assertEquals(2, o.findMaxForm(
                new String[]{ "10", "0", "1" },
                1, 1
        ));
    }
}