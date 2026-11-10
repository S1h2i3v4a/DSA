/*
QUESTION:-
Given an array having both positive and negative integers. The task is to compute the length of the largest subarray with sum 0.

Example:
Input: N = 8, A[] = {15,-2,2,-8,1,7,10,23}
Output: 5
Explanation: The largest subarray with sum 0 is -2 2 -8 1 7.
*/

/*
APPROACH:-
Prefix Sum with HashMap: Store earliest index where each prefix sum appears. If prefixSum == 0, maxLen = i + 1. If prefixSum exists in map, update maxLen = max(maxLen, i - map.get(prefixSum)).
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    int maxLen(int arr[], int n) {
        Map<Integer, Integer> map = new HashMap<>();
        int maxLen = 0, sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
            if (sum == 0) {
                maxLen = i + 1;
            } else if (map.containsKey(sum)) {
                maxLen = Math.max(maxLen, i - map.get(sum));
            } else {
                map.put(sum, i);
            }
        }
        return maxLen;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(N)
