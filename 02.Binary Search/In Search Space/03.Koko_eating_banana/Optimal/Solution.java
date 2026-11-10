/*
QUESTION:-
Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas. The guards have gone and will come back in h hours.
Return the minimum integer k such that she can eat all the bananas within h hours.

Example:
Input: piles = [3,6,7,11], h = 8
Output: 4
*/

/*
APPROACH:-
Binary Search on Answer: The answer lies in range [1, max(piles)]. Use binary search: if speed mid can finish within h hours, record ans = mid and try smaller speed (high = mid - 1); else low = mid + 1.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int low = 1, high = 0;
        for (int pile : piles) high = Math.max(high, pile);
        int ans = high;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            long hours = calculateHours(piles, mid);
            if (hours <= h) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    private long calculateHours(int[] piles, int speed) {
        long total = 0;
        for (int pile : piles) {
            total += (pile + speed - 1) / speed;
        }
        return total;
    }
}

// TIME COMPLEXITY = O(N * log(max(piles)))
// SPACE COMPLEXITY = O(1)
