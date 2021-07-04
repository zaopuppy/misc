package com.zero.algorithm.poj;

/**
 * http://poj.org/problem?id=2251
 */
public class DungeonMaster {
    /**
     * 1st layer, 2nd row, 3rd column
     *
     * dungeon[1][2][3]
     *
     * @param dungeon dungeon
     * @return minimum path length
     */
    public int prisonBreak(char[][][] dungeon) {
        // dp[p1][p2] is the minimum path from `p1` to `p2`
        // dp[p1][p2] = min(
        //     dp[p1][p2 + (-1,  0,  0)],
        //     dp[p1][p2 + ( 0, -1,  0)],
        //     dp[p1][p2 + ( 0,  0, -1)],
        //     dp[p1][p2 + ( 1,  0,  0)],
        //     dp[p1][p2 + ( 0,  1,  0)],
        //     dp[p1][p2 + ( 0,  0,  1)])
        return -1;
    }
}
