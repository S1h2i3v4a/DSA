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
Linear Search: Try every eating speed k from 1 up to max(piles). Calculate total hours required. Return the first k where total hours <= h.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int maxPile = 0;
        for (int pile : piles) maxPile = Math.max(maxPile, pile);

        for (int k = 1; k <= maxPile; k++) {
            long hours = calculateHours(piles, k);
            if (hours <= h) return k;
        }
        return maxPile;
    }

    private long calculateHours(int[] piles, int speed) {
        long total = 0;
        for (int pile : piles) {
            total += (pile + speed - 1) / speed;
        }
        return total;
    }
}

// TIME COMPLEXITY = O(max(piles) * N)
// SPACE COMPLEXITY = O(1)
