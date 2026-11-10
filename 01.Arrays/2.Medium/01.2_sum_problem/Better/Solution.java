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
Use a HashMap to store element values and their indices. For each element nums[i], check if (target - nums[i]) exists in the map.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        return new int[] {};
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(N)
