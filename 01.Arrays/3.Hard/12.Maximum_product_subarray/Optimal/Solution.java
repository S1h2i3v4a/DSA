/*
QUESTION:-
Given an integer array nums, find a subarray that has the largest product, and return the product.

Example:
Input: nums = [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.
*/

/*
APPROACH:-
Prefix and Suffix Traversal: Calculate prefix product from left to right and suffix product from right to left. If product hits 0, reset to 1. The maximum product will be the max value seen during either pass.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int maxProduct(int[] nums) {
        int n = nums.length;
        int max = Integer.MIN_VALUE;
        int prefix = 1, suffix = 1;
        for (int i = 0; i < n; i++) {
            if (prefix == 0) prefix = 1;
            if (suffix == 0) suffix = 1;
            prefix *= nums[i];
            suffix *= nums[n - 1 - i];
            max = Math.max(max, Math.max(prefix, suffix));
        }
        return max;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
