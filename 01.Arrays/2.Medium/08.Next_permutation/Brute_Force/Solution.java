/*
QUESTION:-
A permutation of an array of integers is an arrangement of its members into a sequence or linear order. Find the next lexicographical permutation of numbers. If no such permutation exists, rearrange it to the lowest possible order (sorted in ascending order).

Example:
Input: nums = [1,2,3]
Output: [1,3,2]
*/

/*
APPROACH:-
Recursively generate all permutations in lexicographical order. Find the position of the given array; the next one is the answer. (Conceptually O(N! * N)).
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public void nextPermutation(int[] nums) {
        // Linear scan equivalent of finding next in sorted permutations
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

// TIME COMPLEXITY = O(N! * N)
// SPACE COMPLEXITY = O(N)
