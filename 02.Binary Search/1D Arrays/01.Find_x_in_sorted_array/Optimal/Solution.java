/*
QUESTION:-
Given an array of integers nums which is sorted in ascending order, and an integer target, write a function to search target in nums. If target exists, then return its index. Otherwise, return -1.

Example 1:
Input: nums = [-1,0,3,5,9,12], target = 9
Output: 4
Explanation: The target value 9 exists in the nums array, and its index is 4.

Example 2:
Input: nums = [-1,0,3,5,9,12], target = 2
Output: -1
Explanation: The target value 2 does not exist in the nums array, so return -1.
*/

/*
APPROACH:-
Binary Search: Since the array is sorted, maintain two pointers low and high. Calculate mid = low + (high - low)/2. If nums[mid] == target, return mid. If nums[mid] < target, eliminate left half by low = mid + 1. Otherwise eliminate right half by high = mid - 1.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int search(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] < target) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }
}

// TIME COMPLEXITY = O(log N)
// SPACE COMPLEXITY = O(1)
