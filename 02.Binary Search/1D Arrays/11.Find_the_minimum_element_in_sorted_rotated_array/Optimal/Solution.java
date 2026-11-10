/*
QUESTION:-
Given the sorted rotated array nums of unique elements, return the minimum element of this array.

Example 1:
Input: nums = [3,4,5,1,2]
Output: 1

Example 2:
Input: nums = [4,5,6,7,0,1,2]
Output: 0
*/

/*
APPROACH:-
Binary Search: In a rotated sorted array, compare nums[low] with nums[mid] and nums[high]. The minimum lies in the unsorted half, or if nums[low] <= nums[high], the range is already sorted and nums[low] is the minimum.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int findMin(int[] nums) {
        int low = 0, high = nums.length - 1, ans = Integer.MAX_VALUE;
        while (low <= high) {
            if (nums[low] <= nums[high]) {
                ans = Math.min(ans, nums[low]);
                break;
            }
            int mid = low + (high - low) / 2;
            if (nums[low] <= nums[mid]) {
                ans = Math.min(ans, nums[low]);
                low = mid + 1;
            } else {
                ans = Math.min(ans, nums[mid]);
                high = mid - 1;
            }
        }
        return ans;
    }
}

// TIME COMPLEXITY = O(log N)
// SPACE COMPLEXITY = O(1)
