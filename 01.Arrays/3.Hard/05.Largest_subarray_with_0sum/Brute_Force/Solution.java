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
Iterate through all pairs (i, j) with two nested loops. Accumulate sum; if sum == 0, update maxLen = max(maxLen, j - i + 1).
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    int maxLen(int arr[], int n) {
        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += arr[j];
                if (sum == 0) maxLen = Math.max(maxLen, j - i + 1);
            }
        }
        return maxLen;
    }
}

// TIME COMPLEXITY = O(N^2)
// SPACE COMPLEXITY = O(1)
