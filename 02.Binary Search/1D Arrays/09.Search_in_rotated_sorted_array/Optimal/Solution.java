/*
QUESTION:-
Given the array nums after possible rotation at an unknown pivot and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.

Example 1:
Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4

Example 2:
Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1
*/

/*
APPROACH:-
Modified Binary Search: In a rotated sorted array, at least one half (left or right) is always sorted. Check which half is sorted, then determine if the target lies within the sorted half's range.
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
        return -1;
    }
}

// TIME COMPLEXITY = O(log N)
// SPACE COMPLEXITY = O(1)
