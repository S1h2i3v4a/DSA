/*
Question:
You are given an integer array bloomDay, an integer m, and an integer k. You want to make m bouquets. To make a bouquet, you need to use k adjacent flowers from the garden.

The garden consists of n flowers, where the i-th flower will bloom on the bloomDay[i] day and can be used in exactly one bouquet.

Return the minimum number of days you need to wait to be able to make m bouquets from the garden. If it is impossible to make m bouquets, return -1.

Example:

Input:
bloomDay = [1,10,3,10,2]
m = 3
k = 1

Output:
3

Explanation:
Let us see what happened in the first three days. x means flower bloomed and _ means flower did not bloom in the garden.
We need 3 bouquets each should contain 1 flower.
After day 1: [x, _, _, _, _]   // we can only make one bouquet.
After day 2: [x, _, _, _, x]   // we can only make two bouquets.
After day 3: [x, _, x, _, x]   // we can make 3 bouquets. The answer is 3.
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

// TIME COMPLEXITY = The binary search approach takes O(log n), where n is the number of elements in the `bloomDay` array.
// SPACE COMPLEXITY = The space complexity is O(1) since we are using a constant amount of extra space.
