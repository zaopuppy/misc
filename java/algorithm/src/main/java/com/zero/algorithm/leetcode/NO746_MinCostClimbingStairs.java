package com.zero.algorithm.leetcode;

import java.util.stream.IntStream;

/**
 * https://leetcode-cn.com/problems/min-cost-climbing-stairs/
 */
public class NO746_MinCostClimbingStairs {

    /**
     *
     * f(costs, n, n) = 0
     * f(costs, n-1, n) = 0
     *
     * f(costs, i, n) = min(
     *          f(costs, i+1, n) + costs[i+1],
     *          f(costs, i+2, n) + costs[i+2])
     *
     * f(costs, n-2, n) = min(f(costs, (n-2)+1, n) + costs[n-2+1],
     *                        f(costs, (n-2)+2, n) + costs[n-2+2])
     *                  = min(f(costs, n-1, n) + costs[n-1],
     *                        f(costs, n, n) + costs[n]
     *                  = min(costs[n-1], costs[n])
     *
     * @param costs cost list
     * @return minimal cost
     */
    public int minCostClimbingStairs(int[] costs) {

        int n = costs.length;

        int cost = 0;
        {
            int last = 0; // n-2
            int lastOfLast = 0; // n-1
            int cost1 = 0;
            for (int i = n - 3; i >= -1; --i) {
                cost1 = Math.min(
                        last + costs[i + 1],
                        lastOfLast + costs[i + 2]
                );
                lastOfLast = last;
                last = cost1;
            }
            cost = cost1;
        }
        return cost;
    }
}
