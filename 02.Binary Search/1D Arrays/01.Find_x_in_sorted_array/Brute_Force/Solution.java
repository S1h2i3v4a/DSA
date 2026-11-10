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
Linear Search: Iterate through the array sequentially and check each element. If nums[i] == target, return i. If loop ends without finding target, return -1.
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
