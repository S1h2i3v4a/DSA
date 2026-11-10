/*
QUESTION:-
Given an integer array nums that may contain duplicates, return all possible subsets (the power set).
The solution set must not contain duplicate subsets. Return the solution in any order.

Example:
Input: nums = [1,2,2]
Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
*/

/*
APPROACH:-
Recursion with Hash Set: Generate all subsets using standard pick/don't pick recursion, sort each subset, and insert into a HashSet of lists to eliminate duplicates.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        Set<List<Integer>> set = new HashSet<>();
        generate(0, nums, new ArrayList<>(), set);
        return new ArrayList<>(set);
    }

    private void generate(int idx, int[] nums, List<Integer> current, Set<List<Integer>> set) {
        if (idx == nums.length) {
            set.add(new ArrayList<>(current));
            return;
        }
        current.add(nums[idx]);
        generate(idx + 1, nums, current, set);
        current.remove(current.size() - 1);
        generate(idx + 1, nums, current, set);
    }
}

// TIME COMPLEXITY = O(2^N * N log(Set))
// SPACE COMPLEXITY = O(2^N * N)
