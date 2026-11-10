/*
QUESTION:-
A peak element is an element that is strictly greater than its neighbors.
Given a 0-indexed integer array nums, find a peak element, and return its index.

Example:
Input: nums = [1,2,3,1]
Output: 2
Explanation: 3 is a peak element and its index is 2.
*/

/*
APPROACH:-
Linear Scan: Check every element. An element at index i is a peak if it is strictly greater than its left neighbor (if i > 0) and strictly greater than its right neighbor (if i < n - 1).
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int findPeakElement(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            boolean leftOk = (i == 0 || nums[i] > nums[i - 1]);
            boolean rightOk = (i == n - 1 || nums[i] > nums[i + 1]);
            if (leftOk && rightOk) return i;
        }
        return 0;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
