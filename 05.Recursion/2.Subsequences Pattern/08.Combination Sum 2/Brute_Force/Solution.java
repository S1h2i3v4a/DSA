/*
QUESTION:-
Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sum to target.
Each number in candidates may only be used once in the combination.

Example:
Input: candidates = [10,1,2,7,6,1,5], target = 8
Output: [[1,1,6],[1,2,5],[1,7],[2,6]]
*/

/*
APPROACH:-
Recursion with Hash Set: Generate all combinations with pick and don't pick, storing lists in a HashSet to filter duplicate combinations.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        Set<List<Integer>> set = new HashSet<>();
        helper(0, candidates, target, new ArrayList<>(), set);
        return new ArrayList<>(set);
    }

    private void helper(int idx, int[] candidates, int target, List<Integer> current, Set<List<Integer>> set) {
        if (target == 0) {
            set.add(new ArrayList<>(current));
            return;
        }
        if (idx == candidates.length || target < 0) return;

        current.add(candidates[idx]);
        helper(idx + 1, candidates, target - candidates[idx], current, set);
        current.remove(current.size() - 1);
        helper(idx + 1, candidates, target, current, set);
    }
}

// TIME COMPLEXITY = O(2^N * N)
// SPACE COMPLEXITY = O(2^N * N)
