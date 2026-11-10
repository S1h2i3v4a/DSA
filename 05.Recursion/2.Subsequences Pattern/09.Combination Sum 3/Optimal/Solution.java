/*
QUESTION:-
Find all valid combinations of k numbers that sum up to n such that:
Only numbers 1 through 9 are used.
Each number is used at most once.
Return a list of all possible valid combinations.

Example:
Input: k = 3, n = 7
Output: [[1,2,4]]
*/

/*
APPROACH:-
Backtracking in Range [1, 9]: Recursively choose digits from start to 9. If current length reaches k and remaining sum is 0, add to result.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(1, k, n, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int start, int k, int n, List<Integer> current, List<List<Integer>> result) {
        if (current.size() == k && n == 0) {
            result.add(new ArrayList<>(current));
            return;
        }
        if (current.size() >= k || n < 0) return;

        for (int i = start; i <= 9; i++) {
            if (i > n) break;
            current.add(i);
            backtrack(i + 1, k, n - i, current, result);
            current.remove(current.size() - 1);
        }
    }
}

// TIME COMPLEXITY = O(9! / (9-k)!)
// SPACE COMPLEXITY = O(k)
