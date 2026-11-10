/*
QUESTION:-
Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations of candidates where the chosen numbers sum to target. You may return the combinations in any order.
The same number may be chosen from candidates an unlimited number of times.

Example:
Input: candidates = [2,3,6,7], target = 7
Output: [[2,2,3],[7]]
*/

/*
APPROACH:-
Backtracking with Index Retention: At each index, branch into pick (subtract candidate[idx] from target and stay on the same index since elements can be reused) or don't pick (advance index by 1).
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        findCombinations(0, candidates, target, new ArrayList<>(), result);
        return result;
    }

    private void findCombinations(int ind, int[] arr, int target, List<Integer> ds, List<List<Integer>> ans) {
        if (ind == arr.length) {
            if (target == 0) ans.add(new ArrayList<>(ds));
            return;
        }
        if (arr[ind] <= target) {
            ds.add(arr[ind]);
            findCombinations(ind, arr, target - arr[ind], ds, ans);
            ds.remove(ds.size() - 1);
        }
        findCombinations(ind + 1, arr, target, ds, ans);
    }
}

// TIME COMPLEXITY = O(2^T * K) where T is target / min(candidates)
// SPACE COMPLEXITY = O(K * X) where K is average combination length
