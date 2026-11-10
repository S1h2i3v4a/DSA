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
Sort & Backtracking without duplicates: Sort the candidates. Use a loop from start to end; skip duplicate elements at the same depth if i > start && candidates[i] == candidates[i - 1].
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        findCombinations(0, candidates, target, new ArrayList<>(), result);
        return result;
    }

    private void findCombinations(int ind, int[] arr, int target, List<Integer> ds, List<List<Integer>> ans) {
        if (target == 0) {
            ans.add(new ArrayList<>(ds));
            return;
        }
        for (int i = ind; i < arr.length; i++) {
            if (i > ind && arr[i] == arr[i - 1]) continue;
            if (arr[i] > target) break;
            ds.add(arr[i]);
            findCombinations(i + 1, arr, target - arr[i], ds, ans);
            ds.remove(ds.size() - 1);
        }
    }
}

// TIME COMPLEXITY = O(2^N * N)
// SPACE COMPLEXITY = O(N)
