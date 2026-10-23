/*
QUESTION:-
Given an array of non-negative integers, and a value sum, determine if there is a subset of the given set with sum equal to given sum.

Example:
Input:
N = 6
arr[] = {3, 34, 4, 12, 5, 2}
sum = 9
Output: 1
Explanation: Here there exists a subset with sum = 9, 4+3+2 = 9.

Approach:
1. We can solve this problem using dynamic programming with memoization (top-down approach).
2. We define a helper function fmemo(i, sum, arr, dp) that checks if there is a subset of elements up to index i whose sum is equal to the given sum.
3. The function checks if the target sum is 0. If yes, it means we have found a subset with the given sum and returns true.
4. If i is 0, it means we are at the first element, so the function checks if the first element is equal to the target sum.
5. If the dp value for the current index and sum is already calculated, it returns the stored value.
6. If the current element (arr[i]) is less than or equal to the target sum, the function recursively checks for two cases: including the current element in the subset (by reducing the sum) or excluding it (by not changing the sum).
7. If any of the above cases returns true, the dp value for the current index and sum is updated to true.
8. Finally, we return the dp value for the last index and the given sum.

Complexity Analysis:
- The time complexity of the fmemo function is O(n * sum), where n is the number of elements in the array and sum is the target sum. This is because the function calculates the result for each index and sum only once and stores it in the dp array.
- The space complexity is also O(n * sum) to store the dp array.

*/

import java.io.*;
import java.util.*;
import java.util.Arrays;

// CODE:-
class SubsetSumProblem {

    // ==========================================
    // Approach 1: Top-Down DP (Memoization)
    // ==========================================
    public static boolean isSubsetSumMemo(int[] arr, int target) {
        int n = arr.length;
        int[][] dp = new int[n][target + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return memoHelper(n - 1, target, arr, dp);
    }

    private static boolean memoHelper(int index, int target, int[] arr, int[][] dp) {
        if (target == 0) return true;
        if (index == 0) return arr[0] == target;
        if (dp[index][target] != -1) return dp[index][target] == 1;

        boolean notPick = memoHelper(index - 1, target, arr, dp);
        boolean pick = false;
        if (arr[index] <= target) {
            pick = memoHelper(index - 1, target - arr[index], arr, dp);
        }

        boolean result = notPick || pick;
        dp[index][target] = result ? 1 : 0;
        return result;
    }

    // ==========================================
    // Approach 2: Bottom-Up DP (Tabulation)
    // ==========================================
    public static boolean isSubsetSumTabulation(int[] arr, int target) {
        int n = arr.length;
        boolean[][] dp = new boolean[n][target + 1];

        // Target 0 can always be achieved with empty subset
        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }

        // Base case for index 0
        if (arr[0] <= target) {
            dp[0][arr[0]] = true;
        }

        for (int i = 1; i < n; i++) {
            for (int t = 1; t <= target; t++) {
                boolean notPick = dp[i - 1][t];
                boolean pick = false;
                if (arr[i] <= t) {
                    pick = dp[i - 1][t - arr[i]];
                }
                dp[i][t] = notPick || pick;
            }
        }

        return dp[n - 1][target];
    }

    // ==========================================
    // Approach 3: Space-Optimized DP - O(target) Space
    // ==========================================
    public static boolean isSubsetSumSpaceOptimized(int[] arr, int target) {
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;

        if (arr[0] <= target) {
            dp[arr[0]] = true;
        }

        for (int i = 1; i < arr.length; i++) {
            boolean[] next = new boolean[target + 1];
            next[0] = true;
            for (int t = 1; t <= target; t++) {
                boolean notPick = dp[t];
                boolean pick = false;
                if (arr[i] <= t) {
                    pick = dp[t - arr[i]];
                }
                next[t] = notPick || pick;
            }
            dp = next;
        }

        return dp[target];
    }

    public static void main(String[] args) {
        // Test Case 1:
        // arr = [3, 34, 4, 12, 5, 2], target = 9 -> Expected: true (4 + 5 = 9)
        int[] arr1 = {3, 34, 4, 12, 5, 2};
        int target1 = 9;
        System.out.println("Test 1 (target=9):");
        System.out.println("  Memoization:     " + isSubsetSumMemo(arr1, target1)); // true
        System.out.println("  Tabulation:      " + isSubsetSumTabulation(arr1, target1)); // true
        System.out.println("  Space-Optimized: " + isSubsetSumSpaceOptimized(arr1, target1)); // true

        // Test Case 2:
        // arr = [3, 34, 4, 12, 5, 2], target = 30 -> Expected: false
        int target2 = 30;
        System.out.println("\nTest 2 (target=30):");
        System.out.println("  Memoization:     " + isSubsetSumMemo(arr1, target2)); // false
        System.out.println("  Tabulation:      " + isSubsetSumTabulation(arr1, target2)); // false
        System.out.println("  Space-Optimized: " + isSubsetSumSpaceOptimized(arr1, target2)); // false
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
