/*
QUESTION:-
Given an integer array nums, find the subarray with the largest sum, and return its sum.

Example:
Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
Output: 6
Explanation: The subarray [4,-1,2,1] has the largest sum 6.
*/

/*
APPROACH:-
Consider all subarrays by running three nested loops: outer loops i and j for boundaries, inner loop k for computing subarray sum.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int maxSubArray(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                int sum = 0;
                for (int k = i; k <= j; k++) sum += nums[k];
                maxSum = Math.max(maxSum, sum);
            }
        }
        return maxSum;
    }
}

// TIME COMPLEXITY = O(N^3)
// SPACE COMPLEXITY = O(1)
