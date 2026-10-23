/*QUESTION:
Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sum to target. Each number in candidates may only be used once in the combination. Note: The solution set must not contain duplicate combinations.

Example:
Input: candidates = [10,1,2,7,6,1,5], target = 8
Output: [[1,1,6],[1,2,5],[1,7],[2,6]]
Explanation: The sum of the combinations [1,1,6], [1,2,5], [1,7], and [2,6] is equal to the target 8.

Approach:
To find all unique combinations that sum up to the target, we can use a recursive backtracking approach.
1. Create a helper function called 'solve' that takes the current index, the target sum, the array of candidates, a temporary vector to store the current combination, and a vector of vector to store all combinations as parameters.
2. Sort the array of candidates in non-decreasing order to handle duplicates.
3. In the 'solve' function:
   - If the target sum is equal to 0, add the current combination to the vector of combinations.
   - If the current index is equal to the size of the array, return.
   - If the current candidate value is less than or equal to the target sum:
     - Add the current candidate to the current combination.
     - Recursively call the 'solve' function with the updated index by incrementing it, the reduced target sum by subtracting the current candidate value, the same array of candidates, the current combination, and the vector of combinations.
     - Remove the last added candidate from the current combination (backtracking).
   - To handle duplicates, skip the candidates with the same value as the current candidate by incrementing the index until a different candidate is found.
   - Recursively call the 'solve' function with the updated index by incrementing it, the same target sum, the same array of candidates, the current combination, and the vector of combinations.
4. Create the main function called 'combinationSum2' that calculates and returns the vector of combinations.
   - Initialize an empty vector to store the combinations.
   - Call the 'solve' function with the initial index as 0, the target sum, the sorted array of candidates, an empty temporary vector, and the vector of combinations.
   - Return the vector of combinations.

Time Complexity: O(N * 2^N), where N is the size of the array of candidates. In the worst case, we may have to explore all possible combinations, which is exponential.
Space Complexity: O(target), as the maximum depth of the recursion tree is determined by the target sum.

CODE:*/

import java.util.*;
import java.io.*;

// CODE:-
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
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
            if (i > index && candidates[i] == candidates[i - 1]) continue;
            if (candidates[i] > target) break;

            current.add(candidates[i]);
            findCombinations(candidates, i + 1, target - candidates[i], current, result);
            current.remove(current.size() - 1);
        }
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
