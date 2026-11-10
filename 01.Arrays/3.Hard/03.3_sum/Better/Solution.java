/*
QUESTION:-
Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0. The solution set must not contain duplicate triplets.

Example:
Input: nums = [-1,0,1,2,-1,-4]
Output: [[-1,-1,2],[-1,0,1]]
*/

/*
APPROACH:-
Two nested loops with a HashSet for the third element: Fix i and j; the required third element is -(nums[i] + nums[j]). Use a set to look up if the third element was seen between i and j.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> ansSet = new HashSet<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            Set<Integer> hashset = new HashSet<>();
            for (int j = i + 1; j < n; j++) {
                int third = -(nums[i] + nums[j]);
                if (hashset.contains(third)) {
                    List<Integer> temp = Arrays.asList(nums[i], nums[j], third);
                    Collections.sort(temp);
                    ansSet.add(temp);
                }
                hashset.add(nums[j]);
            }
        }
        return new ArrayList<>(ansSet);
    }
}

// TIME COMPLEXITY = O(N^2 * log(no. of unique triplets))
// SPACE COMPLEXITY = O(N) + O(2 * no. of unique triplets)
