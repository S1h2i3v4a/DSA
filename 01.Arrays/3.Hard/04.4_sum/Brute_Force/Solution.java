/*
QUESTION:-
Given an array nums of n integers, return an array of all the unique quadruplets [nums[a], nums[b], nums[c], nums[d]] such that a, b, c, and d are distinct and nums[a] + nums[b] + nums[c] + nums[d] == target.

Example:
Input: nums = [1,0,-1,0,-2,2], target = 0
Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
*/

/*
APPROACH:-
Four nested loops generating all quadruplets, sorting each valid quadruplet and inserting into a HashSet to remove duplicates.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Set<List<Integer>> set = new HashSet<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    for (int l = k + 1; l < n; l++) {
                        long sum = (long) nums[i] + nums[j] + nums[k] + nums[l];
                        if (sum == target) {
                            List<Integer> quad = Arrays.asList(nums[i], nums[j], nums[k], nums[l]);
                            Collections.sort(quad);
                            set.add(quad);
                        }
                    }
                }
            }
        }
        return new ArrayList<>(set);
    }
}

// TIME COMPLEXITY = O(N^4)
// SPACE COMPLEXITY = O(2 * no. of unique quads)
