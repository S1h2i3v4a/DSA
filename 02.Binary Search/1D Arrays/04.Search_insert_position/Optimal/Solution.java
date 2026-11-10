/*
QUESTION:-
Given a sorted array of distinct integers and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

Example 1:
Input: nums = [1,3,5,6], target = 5
Output: 2

Example 2:
Input: nums = [1,3,5,6], target = 2
Output: 1
*/

/*
APPROACH:-
Binary Search (Lower Bound): Find the smallest index where nums[mid] >= target. Maintain low and high pointers; when nums[mid] >= target, ans = mid and high = mid - 1; else low = mid + 1.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int searchInsert(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        int ans = nums.length;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] >= target) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }
}

// TIME COMPLEXITY = O(log N)
// SPACE COMPLEXITY = O(1)
