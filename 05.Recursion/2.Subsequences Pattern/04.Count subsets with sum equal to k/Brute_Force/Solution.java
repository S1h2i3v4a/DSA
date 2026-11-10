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
Pure Recursion: Explore inclusion and exclusion of each element at index i. If sum == 0 when index == n, return 1.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int perfectSum(int arr[], int n, int sum) {
        return helper(arr, 0, sum);
    }

    private int helper(int[] arr, int idx, int target) {
        if (idx == arr.length) {
            return target == 0 ? 1 : 0;
        }
        int pick = 0;
        if (arr[idx] <= target) {
            pick = helper(arr, idx + 1, target - arr[idx]);
        }
        int notPick = helper(arr, idx + 1, target);
        return (pick + notPick) % 1_000_000_007;
    }
}

// TIME COMPLEXITY = O(2^N)
// SPACE COMPLEXITY = O(N) (call stack)
