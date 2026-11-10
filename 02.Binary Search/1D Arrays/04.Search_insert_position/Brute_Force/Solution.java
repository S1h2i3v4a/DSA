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
Linear search: Traverse the array and return the first index where nums[i] >= target. If no such element exists, return nums.length.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int searchInsert(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= target) return i;
        }
        return nums.length;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
