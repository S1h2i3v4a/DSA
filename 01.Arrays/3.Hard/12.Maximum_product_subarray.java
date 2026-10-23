/*QUESTION:

Given an integer array nums, find a subarray that has the largest product, and return the product.

Example:

Input: nums = [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.

APPROACH:

To find the subarray with the largest product, we iterate through the array while keeping track of the current product. We maintain two variables: `ans` to store the maximum product found so far and `prdct` to store the current product. Since negative numbers can change the sign and potentially result in a larger product, we run the loop twice, once from left to right and once from right to left.

CODE:*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int maxProduct(int[] nums) {
        int prod1 = nums[0], prod2 = nums[0], result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int temp = Math.max(nums[i], Math.max(prod1 * nums[i], prod2 * nums[i]));
            prod2 = Math.min(nums[i], Math.min(prod1 * nums[i], prod2 * nums[i]));
            prod1 = temp;
            result = Math.max(result, prod1);
        }
        return result;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
