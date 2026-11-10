/*
QUESTION:-
Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0. The solution set must not contain duplicate triplets.

Example:
Input: nums = [-1,0,1,2,-1,-4]
Output: [[-1,-1,2],[-1,0,1]]
*/

/*
APPROACH:-
Three nested loops: Outer loops pick nums[i], nums[j], and nums[k]. If sum is 0, sort the triplet and insert into a HashSet to filter duplicates.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> set = new HashSet<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> triplet = Arrays.asList(nums[i], nums[j], nums[k]);
                        Collections.sort(triplet);
                        set.add(triplet);
                    }
                }
            }
        }
        return new ArrayList<>(set);
    }
}

// TIME COMPLEXITY = O(N^3)
// SPACE COMPLEXITY = O(2 * no. of unique triplets)
