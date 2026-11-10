/*
QUESTION:-
A conveyor belt has packages that must be shipped within days days. The ith package has weight weights[i]. Return the least weight capacity of the ship that will result in all packages being shipped within days days.

Example:
Input: weights = [1,2,3,4,5,6,7,8,9,10], days = 5
Output: 15
*/

/*
APPROACH:-
Binary Search on Answer: Range is [max(weights), sum(weights)]. If capacity mid takes <= days, record ans = mid and high = mid - 1; else low = mid + 1.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int shipWithinDays(int[] weights, int days) {
        int low = 0, high = 0;
        for (int w : weights) {
            low = Math.max(low, w);
            high += w;
        }
        int ans = high;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (countDays(weights, mid) <= days) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
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

// TIME COMPLEXITY = O(N * log(sum - max))
// SPACE COMPLEXITY = O(1)
