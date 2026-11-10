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
Linear Search: Try every divisor d from 1 up to max(nums). Compute the ceil sum and return the first divisor where sum <= threshold.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int smallestDivisor(int[] nums, int threshold) {
        int maxNum = 0;
        for (int num : nums) maxNum = Math.max(maxNum, num);

        for (int d = 1; d <= maxNum; d++) {
            if (computeSum(nums, d) <= threshold) return d;
        }
        return maxNum;
    }

    private int computeSum(int[] nums, int divisor) {
        int sum = 0;
        for (int num : nums) {
            sum += (num + divisor - 1) / divisor;
        }
        return sum;
    }
}

// TIME COMPLEXITY = O(max(nums) * N)
// SPACE COMPLEXITY = O(1)
