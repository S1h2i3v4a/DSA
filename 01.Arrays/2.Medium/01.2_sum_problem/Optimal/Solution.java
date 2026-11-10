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
Sort a 2D array of [value, original_index]. Use two pointers (left at start, right at end). If sum == target, return original indices; if sum < target, left++; if sum > target, right--.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        int[][] pairs = new int[n][2];
        for (int i = 0; i < n; i++) {
            pairs[i][0] = nums[i];
            pairs[i][1] = i;
        }
        Arrays.sort(pairs, (a, b) -> Integer.compare(a[0], b[0]));
        int left = 0, right = n - 1;
        while (left < right) {
            int sum = pairs[left][0] + pairs[right][0];
            if (sum == target) {
                return new int[] { pairs[left][1], pairs[right][1] };
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[] {};
    }
}

// TIME COMPLEXITY = O(N log N)
// SPACE COMPLEXITY = O(N)
