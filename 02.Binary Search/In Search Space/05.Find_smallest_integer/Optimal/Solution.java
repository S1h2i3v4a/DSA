/*
QUESTION:-
Given an array of integers nums and an integer threshold, we will choose a positive integer divisor, divide all the array by it, and sum the division's result. Find the smallest divisor such that the result mentioned above is less than or equal to threshold.

Example:
Input: nums = [1,2,5,9], threshold = 6
Output: 5
Explanation: With divisor 5, sum is 1 + 1 + 1 + 2 = 5 <= 6.
*/

/*
APPROACH:-
Binary Search on Answer: Divisor lies in range [1, max(nums)]. Binary search mid divisor: if sum <= threshold, ans = mid and high = mid - 1; else low = mid + 1.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int smallestDivisor(int[] nums, int threshold) {
        int low = 1, high = 0;
        for (int num : nums) high = Math.max(high, num);
        int ans = high;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (computeSum(nums, mid) <= threshold) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    private int computeSum(int[] nums, int divisor) {
        int sum = 0;
        for (int num : nums) {
            sum += (num + divisor - 1) / divisor;
        }
        return sum;
    }
}

// TIME COMPLEXITY = O(N * log(max(nums)))
// SPACE COMPLEXITY = O(1)
