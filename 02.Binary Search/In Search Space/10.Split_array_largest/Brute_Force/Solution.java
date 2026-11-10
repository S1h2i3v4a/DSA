/*
QUESTION:-
Given an integer array nums and an integer k, split nums into k non-empty subarrays such that the largest sum of any subarray is minimized.
Return the minimized largest sum of the split.

Example:
Input: nums = [7,2,5,10,8], k = 2
Output: 18
*/

/*
APPROACH:-
Linear Search: Range of max subarray sum is [max(nums), sum(nums)]. Linearly test every sum from max(nums) to sum(nums) until countSubarrays(sum) <= k.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int splitArray(int[] nums, int k) {
        int maxVal = 0, sumVal = 0;
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
            sumVal += num;
        }

        for (int s = maxVal; s <= sumVal; s++) {
            if (countSubarrays(nums, s) <= k) return s;
        }
        return sumVal;
    }

    private int countSubarrays(int[] nums, int maxSum) {
        int count = 1, currentSum = 0;
        for (int num : nums) {
            if (currentSum + num > maxSum) {
                count++;
                currentSum = num;
            } else {
                currentSum += num;
            }
        }
        return count;
    }
}

// TIME COMPLEXITY = O((sum - max) * N)
// SPACE COMPLEXITY = O(1)
