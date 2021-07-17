package com.zero.algorithm.leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NO509_FibonacciTest {

    @Test
    void fib() {
        NO509_Fibonacci o = new NO509_Fibonacci();
        for (int i = 0; i < 30; ++i) {
            assertEquals(o.fibOld(i), o.fib(i));
        }
    }

    @Test
    void fibOld() {
        NO509_Fibonacci o = new NO509_Fibonacci();
        assertEquals(0, o.fibOld(0));
        assertEquals(1, o.fibOld(1));
        assertEquals(1, o.fibOld(2));
        assertEquals(2, o.fibOld(3));
        assertEquals(3, o.fibOld(4));
        assertEquals(6765, o.fibOld(20));
        assertEquals(832040, o.fibOld(30));
    }

}
