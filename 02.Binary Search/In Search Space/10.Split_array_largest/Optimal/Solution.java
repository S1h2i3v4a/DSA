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
Binary Search on Answer: Search space is [max(nums), sum(nums)]. If countSubarrays(mid) <= k, record ans = mid and high = mid - 1; else low = mid + 1.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int splitArray(int[] nums, int k) {
        int low = 0, high = 0;
        for (int num : nums) {
            low = Math.max(low, num);
            high += num;
        }
        int ans = high;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (countSubarrays(nums, mid) <= k) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
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

// TIME COMPLEXITY = O(N * log(sum - max))
// SPACE COMPLEXITY = O(1)
