/*
QUESTION:-
Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.
If target is not found in the array, return [-1, -1].

Example:
Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]
*/

/*
APPROACH:-
Linear Scan: Traverse the array from left to right. Record the first occurrence when nums[i] == target. Update last occurrence whenever nums[i] == target.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int first = -1, last = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                if (first == -1) first = i;
                last = i;
            }
        }
        return new int[]{first, last};
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
