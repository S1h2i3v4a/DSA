/*
QUESTION:-
Given an array nums of n integers, return an array of all the unique quadruplets [nums[a], nums[b], nums[c], nums[d]] such that a, b, c, and d are distinct and nums[a] + nums[b] + nums[c] + nums[d] == target.

Example:
Input: nums = [1,0,-1,0,-2,2], target = 0
Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
*/

/*
APPROACH:-
Three nested loops with a HashSet for the fourth element: Fix i, j, k; look up target - (nums[i] + nums[j] + nums[k]) in a hash set populated between j and k.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Set<List<Integer>> ansSet = new HashSet<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                Set<Long> hashset = new HashSet<>();
                for (int k = j + 1; k < n; k++) {
                    long fourth = (long) target - (nums[i] + nums[j] + nums[k]);
                    if (hashset.contains(fourth)) {
                        List<Integer> temp = Arrays.asList(nums[i], nums[j], nums[k], (int) fourth);
                        Collections.sort(temp);
                        ansSet.add(temp);
                    }
                    hashset.add((long) nums[k]);
                }
            }
        }
        return new ArrayList<>(ansSet);
    }
}

// TIME COMPLEXITY = O(N^3)
// SPACE COMPLEXITY = O(N) + O(2 * no. of unique quads)
