/*
 * Problem Name: Combination Sum III
 * Platform: LeetCode (216) / GeeksforGeeks
 * Difficulty: Medium
 * Time Complexity: O(9! / (9-K)!)
 * Space Complexity: O(K)
 */

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(k, n, 1, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int k, int target, int start, List<Integer> current, List<List<Integer>> result) {
        if (current.size() == k) {
            if (target == 0) {
                result.add(new ArrayList<>(current));
            }
            return;
        }

        for (int i = start; i <= 9; i++) {
            if (i > target) break;
            current.add(i);
            backtrack(k, target - i, i + 1, current, result);
            current.remove(current.size() - 1);
        }
    }
}
