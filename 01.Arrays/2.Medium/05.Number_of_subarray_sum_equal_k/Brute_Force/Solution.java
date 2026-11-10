/*
QUESTION:-
Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.

Example:
Input: nums = [1,1,1], k = 2
Output: 2
*/

/*
APPROACH:-
Check all possible subarrays using two nested loops. Accumulate the sum and increment count whenever sum == k.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k) count++;
            }
        }
        return count;
    }
}

// TIME COMPLEXITY = O(N^2)
// SPACE COMPLEXITY = O(1)
