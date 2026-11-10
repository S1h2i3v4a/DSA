/*
QUESTION:-
A permutation of an array of integers is an arrangement of its members into a sequence or linear order. Find the next lexicographical permutation of numbers. If no such permutation exists, rearrange it to the lowest possible order (sorted in ascending order).

Example:
Input: nums = [1,2,3]
Output: [1,3,2]
*/

/*
APPROACH:-
Three-step algorithm: 1. Find pivot i where nums[i] < nums[i+1] from right. 2. Find successor j > i where nums[j] > nums[i] from right, and swap nums[i] with nums[j]. 3. Reverse elements from i+1 to end.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) i--;
        if (i >= 0) {
            int j = nums.length - 1;
            while (nums[j] <= nums[i]) j--;
            swap(nums, i, j);
        }
        reverse(nums, i + 1, nums.length - 1);
    }
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i]; nums[i] = nums[j]; nums[j] = temp;
    }
    private void reverse(int[] nums, int i, int j) {
        while (i < j) swap(nums, i++, j--);
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
