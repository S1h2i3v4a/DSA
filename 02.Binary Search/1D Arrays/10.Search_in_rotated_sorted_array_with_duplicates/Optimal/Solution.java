/*
QUESTION:-
Given an integer array nums sorted in non-decreasing order (which may contain duplicates) rotated at an unknown pivot, and an integer target, return true if target is in nums, or false otherwise.

Example 1:
Input: nums = [2,5,6,0,0,1,2], target = 0
Output: true

Example 2:
Input: nums = [2,5,6,0,0,1,2], target = 3
Output: false
*/

/*
APPROACH:-
Binary Search with Trimming: Similar to Search in Rotated Sorted Array, but when nums[low] == nums[mid] == nums[high], shrink the search space by doing low++ and high--.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public boolean search(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) return true;

            if (nums[low] == nums[mid] && nums[mid] == nums[high]) {
                low++;
                high--;
                continue;
            }

            if (nums[low] <= nums[mid]) {
                if (nums[low] <= target && target < nums[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return false;
    }
}

// TIME COMPLEXITY = O(log N) average, O(N) worst case
// SPACE COMPLEXITY = O(1)
