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
Linear search: Iterate through the array and check if nums[i] == target.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int search(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) return i;
        }
        return -1;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
