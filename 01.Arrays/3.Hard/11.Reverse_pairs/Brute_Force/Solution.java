/*
QUESTION:-
Given an integer array nums, return the number of reverse pairs in the array. A reverse pair is a pair (i, j) where 0 <= i < j < nums.length and nums[i] > 2 * nums[j].

Example:
Input: nums = [1,3,2,3,1]
Output: 2
*/

/*
APPROACH:-
Two nested loops checking every pair (i, j) with i < j. Increment count if (long) nums[i] > 2L * nums[j].
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int reversePairs(int[] nums) {
        int count = 0, n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if ((long) nums[i] > 2L * nums[j]) count++;
            }
        }
        return count;
    }
}

// TIME COMPLEXITY = O(N^2)
// SPACE COMPLEXITY = O(1)
