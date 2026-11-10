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
Evaluate all subarrays with two nested loops. Maintain running product and track maxProduct.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int maxProduct(int[] nums) {
        int max = nums[0];
        for (int i = 0; i < nums.length; i++) {
            int prod = 1;
            for (int j = i; j < nums.length; j++) {
                prod *= nums[j];
                max = Math.max(max, prod);
            }
        }
        return max;
    }
}

// TIME COMPLEXITY = O(N^2)
// SPACE COMPLEXITY = O(1)
