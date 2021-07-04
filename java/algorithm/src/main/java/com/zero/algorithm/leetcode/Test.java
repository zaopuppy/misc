package com.zero.algorithm.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class Test {
    static void log(String msg) {
        System.out.println(msg);
    }

    public static void main(String[] args) {
        final Test o = new Test();
        // int max = o.maxProduct(new int[]{-1,-2,-9,-6});

        // int max = o.maxSubarraySumCircular(new int[] {3,-2,2,-3});
        // log("max(3): " + max);
        // max = o.maxSubarraySumCircular(new int[] {-3,-2,-1});
        // log("max(-1): " + max);
        // max = o.maxSubarraySumCircular(new int[] {5,-3,5});
        // log("max(10): " + max);
        // max = o.maxSubarraySumCircular(new int[] {3,-1,2,-1});
        // log("max(4): " + max);
        // max = o.maxSubarraySumCircular(new int[] {-5,-2,5,6,-2,-7,0,2,8});
        // log("max(14): " + max);

        int[] rect = o.getMaxMatrix(new int[][] {
                {-1, 0},
                {0, -1}
        });

        log(Arrays.toString(rect));
    }

    final Map<Integer, Integer> cache = new HashMap<>();

    boolean hasCache(int w, int h) {
        return cache.containsKey(w*1000 + h);
    }
    int getCache(int w, int h) {
        return cache.get(w*1000 + h);
    }
    void putCache(int w, int h, int v) {
        cache.put(w*1000 + h, v);
    }

    int get2dArray(int[][] array, int h, int w, int def) {
        if (w < 0 || w >= array.length || h < 0 || h >= array[0].length) {
            return def;
        }
        return array[h][w];
    }

    /**
     * r1, c1, r2, c2
     *
     * 其中 r1, c1 分别代表子矩阵左上角的行号和列号，r2, c2 分别代表右下角的行号和列号。若有多个满足条件的子矩阵，返回任意一个均可
     *
     * @param matrix
     * @return
     */
    public int[] getMaxMatrix(int[][] matrix) {
        int height = matrix.length;
        int width = matrix[0].length;

        int max = matrix[0][0];
        int[] maxPos = new int[] { 0, 0, 0, 0 };

        int[] curPos = new int[4];
        int[][] pre = new int[height][width];
        // pre[0][0] = max;
        for (int h = 0; h < height; ++h) {
            for (int w = 0; w < width; ++w) {
                int n = matrix[h][w];
                int pre1 = get2dArray(pre, h-1, w-1, 0);
                int pre2 = get2dArray(pre, h-1, w, 0);
                int pre3 = get2dArray(pre, h, w-1, 0);
                int calPre = pre2 + pre3 - pre1;
                if (calPre > 0) {
                    pre[h][w] = n + calPre;
                } else {
                    pre[h][w] = n;
                    curPos[0] = h;
                    curPos[1] = w;
                }
                curPos[2] = h;
                curPos[3] = w;
                if (pre[h][w] > max) {
                    max = pre[h][w];
                    maxPos = curPos.clone();
                }
            }
        }
        log("max=" + max);
        return maxPos;
    }

    public int maxSubarraySumCircular(int[] nums) {
        if (nums.length <= 0) { return 0; }

        int maxLen = 1;
        int max = nums[0];
        int pre = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            int n = nums[i];
            if (pre + n > n) {
                pre = pre + n;
                maxLen += 1;
            } else {
                pre = n;
                maxLen = 1;
            }
            // pre = Math.max(pre + nums[i], nums[i]);
            max = Math.max(max, pre);
        }
        for (int i = 0; i < nums.length-1; ++i) {
            if (maxLen >= nums.length) {
                pre -= nums[i];
                --maxLen;
                {
                    int j = i+1;
                    while (maxLen > 0) {
                        int tmp = pre - nums[j++ % nums.length];
                        if (tmp > pre) {
                            --maxLen;
                            pre = tmp;
                        } else {
                            break;
                        }
                    }
                }
            }
            int n = nums[i];
            if (pre + n > n) {
                pre = pre + n;
                maxLen += 1;
            } else {
                pre = n;
                maxLen = 1;
            }
            // pre = Math.max(pre + nums[i], nums[i]);
            max = Math.max(max, pre);
        }
        return max;
    }

    public int maxProduct(int[] nums) {
        if (nums.length <= 0) { return 0; }

        int pre1 = nums[0], pre2 = nums[0], max = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            int tmp1 = pre1*nums[i];
            int tmp2 = pre2*nums[i];
            pre1 = Math.max(Math.max(tmp1, tmp2), nums[i]);
            pre2 = Math.min(Math.min(tmp1, tmp2), nums[i]);
            max = Math.max(max, pre1);
        }

        return max;
    }

}
