/*
QUESTION:-
Given an array arr[] of non-negative integers and an integer sum, the task is to count all subsets of the given array with a sum equal to a given sum modulo 10^9 + 7.

Example:
Input: N = 6, arr[] = {5, 2, 3, 10, 6, 8}, sum = 10
Output: 3
Explanation: {5, 2, 3}, {2, 8}, {10}
*/

/*
APPROACH:-
Dynamic Programming (1D Space-Optimized): Maintain dp array of size sum + 1. For each number, iterate backwards from sum down to arr[i], adding dp[j - arr[i]] to dp[j] modulo 10^9 + 7.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int perfectSum(int arr[], int n, int sum) {
        int MOD = 1_000_000_007;
        int[] dp = new int[sum + 1];
        dp[0] = 1;

        for (int num : arr) {
            for (int j = sum; j >= num; j--) {
                dp[j] = (dp[j] + dp[j - num]) % MOD;
            }
        }
        return dp[sum];
    }
}

// TIME COMPLEXITY = O(N * sum)
// SPACE COMPLEXITY = O(sum)
