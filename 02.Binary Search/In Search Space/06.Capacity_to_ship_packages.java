/*
Question:
A conveyor belt has packages that must be shipped from one port to another within days days.

The ith package on the conveyor belt has a weight of weights[i]. Each day, we load the ship with packages on the conveyor belt (in the order given by weights). We may not load more weight than the maximum weight capacity of the ship.

Return the least weight capacity of the ship that will result in all the packages on the conveyor belt being shipped within days days.

Example:

Input: weights = [1,2,3,4,5,6,7,8,9,10], days = 5
Output: 15
Explanation: A ship capacity of 15 is the minimum to ship all the packages in 5 days like this:
1st day: 1, 2, 3, 4, 5
2nd day: 6, 7
3rd day: 8
4th day: 9
5th day: 10

Approach:
To find the least weight capacity of the ship, we can use binary search. We set the low and high as the minimum and maximum weight from the weights array, respectively. Then, we iterate through the weights and check if it can be accommodated within the current capacity. If it can, we subtract the weight from the capacity. If it cannot, we increment the required number of days and update the capacity with the current weight. We continue this process until we find the minimum capacity that satisfies the given number of days.

Code:
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
        int days = 1, currentLoad = 0;
        for (int w : weights) {
            if (currentLoad + w > cap) {
                days++;
                currentLoad = w;
            } else {
                currentLoad += w;
            }
        }
        return days;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
