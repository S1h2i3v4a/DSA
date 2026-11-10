/*
QUESTION:-
Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.

Example:
Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Explanation: nums[0] + nums[1] == 9, return [0, 1].
*/

/*
APPROACH:-
Run two nested loops. Outer loop picks nums[i], inner loop checks every nums[j] (j > i) to see if nums[i] + nums[j] == target.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[] { i, j };
                }
            }
        }
        return new int[] {};
    }
}

// TIME COMPLEXITY = O(N^2)
// SPACE COMPLEXITY = O(1)
