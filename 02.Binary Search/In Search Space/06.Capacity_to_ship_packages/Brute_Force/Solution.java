/*
QUESTION:-
A conveyor belt has packages that must be shipped within days days. The ith package has weight weights[i]. Return the least weight capacity of the ship that will result in all packages being shipped within days days.

Example:
Input: weights = [1,2,3,4,5,6,7,8,9,10], days = 5
Output: 15
*/

/*
APPROACH:-
Linear Search: Range of capacity is [max(weights), sum(weights)]. Check each capacity sequentially and return the first capacity that can ship within days.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int shipWithinDays(int[] weights, int days) {
        int maxW = 0, sumW = 0;
        for (int w : weights) {
            maxW = Math.max(maxW, w);
            sumW += w;
        }

        for (int cap = maxW; cap <= sumW; cap++) {
            if (countDays(weights, cap) <= days) return cap;
        }
        return sumW;
    }

    private int countDays(int[] weights, int cap) {
        int d = 1, currentLoad = 0;
        for (int w : weights) {
            if (currentLoad + w > cap) {
                d++;
                currentLoad = w;
            } else {
                currentLoad += w;
            }
        }
        return d;
    }
}

// TIME COMPLEXITY = O((sum - max) * N)
// SPACE COMPLEXITY = O(1)
