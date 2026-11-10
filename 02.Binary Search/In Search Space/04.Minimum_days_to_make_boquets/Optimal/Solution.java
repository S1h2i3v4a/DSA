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
Binary Search on Answer: The answer lies in range [min(bloomDay), max(bloomDay)]. Use binary search to find the minimum day such that canMake(day) returns true.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int minDays(int[] bloomDay, int m, int k) {
        long val = (long) m * k;
        if (val > bloomDay.length) return -1;

        int low = Integer.MAX_VALUE, high = Integer.MIN_VALUE;
        for (int day : bloomDay) {
            low = Math.min(low, day);
            high = Math.max(high, day);
        }
        int ans = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (canMake(bloomDay, mid, m, k)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
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

// TIME COMPLEXITY = O(N * log(maxDay - minDay))
// SPACE COMPLEXITY = O(1)
