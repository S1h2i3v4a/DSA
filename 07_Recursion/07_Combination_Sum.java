/*
 * Problem Name: Combination Sum I
 * Platform: LeetCode (39) / GeeksforGeeks
 * Difficulty: Medium
 * Time Complexity: O(2^T)
 * Space Complexity: O(T)
 */

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        findCombinations(candidates, 0, target, new ArrayList<>(), result);
        return result;
    }

    private void findCombinations(int[] candidates, int index, int target, List<Integer> current, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            if (candidates[i] <= target) {
                current.add(candidates[i]);
                findCombinations(candidates, i, target - candidates[i], current, result);
                current.remove(current.size() - 1);
            }
        }
    }
}
