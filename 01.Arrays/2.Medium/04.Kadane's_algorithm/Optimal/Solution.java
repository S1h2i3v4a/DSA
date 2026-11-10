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
Kadane's Algorithm: Maintain running sum and maxSum. Add nums[i] to sum and update maxSum = max(maxSum, sum). If sum < 0, reset sum to 0.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int maxSubArray(int[] nums) {
        int maxSum = nums[0];
        int sum = 0;
        for (int x : nums) {
            sum += x;
            maxSum = Math.max(maxSum, sum);
            if (sum < 0) sum = 0;
        }
        return maxSum;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
