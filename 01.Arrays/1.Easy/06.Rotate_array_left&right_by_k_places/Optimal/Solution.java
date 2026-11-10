/*
QUESTION:-
Given an array, rotate the array to the right by k steps, where k is non-negative.

Example:
Input: nums = [1,2,3,4,5,6,7], k = 3
Output: [5,6,7,1,2,3,4]
*/

/*
APPROACH:-
Reversal algorithm: 1. Reverse entire array (0 to n-1). 2. Reverse first k elements (0 to k-1). 3. Reverse remaining n-k elements (k to n-1).
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k %= n;
        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }
    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start++] = nums[end];
            nums[end--] = temp;
        }
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
