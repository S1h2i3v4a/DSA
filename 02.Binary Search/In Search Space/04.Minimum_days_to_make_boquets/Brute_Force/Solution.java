/*
QUESTION:-
You are given an integer array bloomDay, an integer m and an integer k.
You want to make m bouquets. To make a bouquet, you need to use k adjacent flowers from the garden.
Return the minimum number of days you need to wait to be able to make m bouquets from the garden. If it is impossible, return -1.

Example:
Input: bloomDay = [1,10,3,10,2], m = 3, k = 1
Output: 3
*/

/*
APPROACH:-
Linear Search: If m * k > n, impossible, return -1. Check every day from min(bloomDay) to max(bloomDay) sequentially. Return the first day that yields >= m bouquets.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int minDays(int[] bloomDay, int m, int k) {
        long val = (long) m * k;
        if (val > bloomDay.length) return -1;

        int minDay = Integer.MAX_VALUE, maxDay = Integer.MIN_VALUE;
        for (int day : bloomDay) {
            minDay = Math.min(minDay, day);
            maxDay = Math.max(maxDay, day);
        }

        for (int day = minDay; day <= maxDay; day++) {
            if (canMake(bloomDay, day, m, k)) return day;
        }
        return -1;
    }

    private boolean canMake(int[] bloomDay, int day, int m, int k) {
        int count = 0, bouquets = 0;
        for (int b : bloomDay) {
            if (b <= day) {
                count++;
                if (count == k) {
                    bouquets++;
                    count = 0;
                }
            } else {
                count = 0;
            }
        }
        return bouquets >= m;
    }
}

// TIME COMPLEXITY = O((maxDay - minDay) * N)
// SPACE COMPLEXITY = O(1)
