/*
QUESTION:-
A peak element is an element that is strictly greater than its neighbors.
Given a 0-indexed integer array nums, find a peak element, and return its index.

Example:
Input: nums = [1,2,3,1]
Output: 2
Explanation: 3 is a peak element and its index is 2.
*/

/*
APPROACH:-
Binary Search: If nums[mid] > nums[mid - 1] and nums[mid] > nums[mid + 1], mid is a peak. If nums[mid] < nums[mid + 1], a peak must exist on the right slope (low = mid + 1); otherwise it must exist on the left slope (high = mid - 1).
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int findPeakElement(int[] nums) {
        int n = nums.length;
        if (n == 1) return 0;
        if (nums[0] > nums[1]) return 0;
        if (nums[n - 1] > nums[n - 2]) return n - 1;

        int low = 1, high = n - 2;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {
                return mid;
            }
            if (nums[mid] > nums[mid - 1]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }
}

// TIME COMPLEXITY = O(log N)
// SPACE COMPLEXITY = O(1)
