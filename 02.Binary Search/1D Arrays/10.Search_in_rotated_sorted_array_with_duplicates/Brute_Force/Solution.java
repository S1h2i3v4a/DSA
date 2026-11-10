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
Linear search: Check each element in the array.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public boolean search(int[] nums, int target) {
        for (int num : nums) {
            if (num == target) return true;
        }
        return false;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
