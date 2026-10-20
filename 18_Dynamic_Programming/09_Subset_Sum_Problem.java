/**
 * Problem Name: Subset Sum Problem
 * Platform: GeeksforGeeks / Standard Data Structures & Algorithms
 * Difficulty: Medium
 *
 * Time Complexity: O(N * target) where N is array length and target is desired sum
 * Space Complexity:
 *   - Memoization: O(N * target) table + O(N) recursion stack
 *   - Tabulation: O(N * target) boolean matrix
 *   - Space-Optimized DP: O(target) 1D boolean array
 *
 * Approach:
 *   - Given an array of positive integers and a target sum, determine if there exists
 *     a subset whose elements sum to `target`.
 *   - At each index `i`, we make a 0-1 choice:
 *     1. Not Pick: subproblem becomes subsetSum(i - 1, target)
 *     2. Pick (if arr[i] <= target): subproblem becomes subsetSum(i - 1, target - arr[i])
 *   - Recurrence:
 *     dp[i][target] = dp[i - 1][target] || dp[i - 1][target - arr[i]]
 *   - Base Cases:
 *     - If target == 0: return true (empty subset always sums to 0).
 *     - If index == 0: return arr[0] == target.
 *   - Space Optimization:
 *     Since dp[i][target] only depends on row i-1, we can maintain a single 1D array
 *     and traverse `target` backwards from `sum` down to `arr[i]`.
 */

import java.util.Arrays;

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
