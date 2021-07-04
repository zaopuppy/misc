package com.zero.algorithm.leetcode;

import java.util.*;


/**
 * dp[i]: maximum sequence of envelops, which ends with a[i]
 * dp[i] =
 */
public class RussiaTaowa {
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes.length <= 0) { return 0; }

        // Arrays.sort(envelopes, (o1, o2) -> {
        //     if (o1[0] == o2[0]) {
        // });
        return 0;
    }

    private static class Envelop {
        final int w;
        final int h;

        Envelop(int w, int h) {
            this.w = w;
            this.h = h;
        }
    }

    public int maxEnvelopesLegacy(int[][] envelopes) {
        final ArrayList<Envelop> l = toList(envelopes);

        return envNum(l);
    }

    private int envNum(ArrayList<Envelop> l) {
        if (l.isEmpty()) {
            return 0;
        }

        int max = 0;
        for (Envelop env: l) {
            ArrayList<Envelop> ll = exclude(l, env);
            max = Math.max(max, envNum(ll, env));
        }
        return max + 1;
    }

    private int envNum(ArrayList<Envelop> l, Envelop env) {
        int max = 0;
        for (Envelop e: l) {
            if (canPutIn(env, e)) {
                ArrayList<Envelop> ll = exclude(l, e);
                max = Math.max(envNum(ll, e) + 1, max);
            }
        }
        return max;
    }

    private boolean canPutIn(int[] one, int[] theOther) {
        return theOther[0] > one[0] && theOther[1] > one[1];
    }

    private boolean canPutIn(Envelop one, Envelop theOther) {
        return theOther.w > one.w && theOther.h > one.h;
    }

    private ArrayList<Envelop> exclude(ArrayList<Envelop> l, Envelop env) {
        ArrayList<Envelop> ll = new ArrayList<>();
        for (Envelop e: l) {
            if (e != env) {
                ll.add(e);
            }
        }
        return ll;
    }

    private ArrayList<Envelop> toList(int[][] envelopes) {
        ArrayList<Envelop> l = new ArrayList<>();
        for (int[] d: envelopes) {
            l.add(new Envelop(d[0], d[1]));
        }
        return l;
    }
}
