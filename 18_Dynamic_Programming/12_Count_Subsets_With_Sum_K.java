/**
 * Problem Name: Count Subsets with Sum K (Perfect Sum Problem)
 * Platform: GeeksforGeeks / Coding Ninjas
 * Difficulty: Medium
 *
 * Time Complexity: O(N * target) where N is array length and target is desired sum
 * Space Complexity:
 *   - Memoization: O(N * target) table + O(N) recursion stack
 *   - Tabulation: O(N * target) table
 *   - Space-Optimized DP: O(target) 1D array
 *
 * Approach:
 *   - Count all subsets of array `arr` whose sum equals `target`.
 *   - Result is returned modulo 10^9 + 7.
 *   - Handling 0s in the array:
 *     - If an element is 0, it can either be included or excluded without changing the sum.
 *     - At index 0:
 *       - If target == 0 && arr[0] == 0: 2 ways (pick 0 or don't pick 0).
 *       - If target == 0 || arr[0] == target: 1 way.
 *       - Else: 0 ways.
 *   - Recurrence:
 *     count(i, target) = (count(i - 1, target) + count(i - 1, target - arr[i])) % MOD
 *   - We provide:
 *     1. Top-Down DP with Memoization
 *     2. Bottom-Up DP with Tabulation
 *     3. Space-Optimized DP using an O(target) 1D array.
 */

import java.util.Arrays;

class CountSubsetsWithSumK {

    private static final int MOD = 1000000007;

    // ==========================================
    // Approach 1: Top-Down DP (Memoization)
    // ==========================================
    public static int countSubsetsMemo(int[] arr, int target) {
        int n = arr.length;
        int[][] dp = new int[n][target + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return memoHelper(n - 1, target, arr, dp);
    }

    private static int memoHelper(int index, int target, int[] arr, int[][] dp) {
        // Base case at index 0
        if (index == 0) {
            if (target == 0 && arr[0] == 0) return 2; // Two choices: pick or not pick 0
            if (target == 0 || arr[0] == target) return 1;
            return 0;
        }

        if (dp[index][target] != -1) {
            return dp[index][target];
        }

        int notPick = memoHelper(index - 1, target, arr, dp);
        int pick = 0;
        if (arr[index] <= target) {
            pick = memoHelper(index - 1, target - arr[index], arr, dp);
        }

        return dp[index][target] = (notPick + pick) % MOD;
    }

    // ==========================================
    // Approach 2: Bottom-Up DP (Tabulation)
    // ==========================================
    public static int countSubsetsTabulation(int[] arr, int target) {
        int n = arr.length;
        int[][] dp = new int[n][target + 1];

        // Base case for index 0
        if (arr[0] == 0) {
            dp[0][0] = 2; // Pick 0 or not pick 0
        } else {
            dp[0][0] = 1; // Not pick arr[0]
            if (arr[0] <= target) {
                dp[0][arr[0]] = 1; // Pick arr[0]
            }
        }

        for (int i = 1; i < n; i++) {
            for (int t = 0; t <= target; t++) {
                int notPick = dp[i - 1][t];
                int pick = 0;
                if (arr[i] <= t) {
                    pick = dp[i - 1][t - arr[i]];
                }
                dp[i][t] = (notPick + pick) % MOD;
            }
        }

        return dp[n - 1][target];
    }

    // ==========================================
    // Approach 3: Space-Optimized DP - O(target) Space
    // ==========================================
    public static int countSubsetsSpaceOptimized(int[] arr, int target) {
        int[] dp = new int[target + 1];

        if (arr[0] == 0) {
            dp[0] = 2;
        } else {
            dp[0] = 1;
            if (arr[0] <= target) {
                dp[arr[0]] = 1;
            }
        }

        for (int i = 1; i < arr.length; i++) {
            int[] next = new int[target + 1];
            for (int t = 0; t <= target; t++) {
                int notPick = dp[t];
                int pick = 0;
                if (arr[i] <= t) {
                    pick = dp[t - arr[i]];
                }
                next[t] = (notPick + pick) % MOD;
            }
            dp = next;
        }

        return dp[target];
    }

    public static void main(String[] args) {
        // Test Case 1:
        // arr = [1, 2, 3, 3], target = 6
        // Subsets summing to 6: [1, 2, 3], [1, 2, 3], [3, 3] -> 3 subsets
        int[] arr1 = {1, 2, 3, 3};
        int target1 = 6;
        System.out.println("Test 1 (arr=[1,2,3,3], target=6):");
        System.out.println("  Memoization:     " + countSubsetsMemo(arr1, target1)); // 3
        System.out.println("  Tabulation:      " + countSubsetsTabulation(arr1, target1)); // 3
        System.out.println("  Space-Optimized: " + countSubsetsSpaceOptimized(arr1, target1)); // 3

        // Test Case 2 (with zeros):
        // arr = [0, 0, 1], target = 1
        // Subsets summing to 1: [1], [0, 1], [0, 1], [0, 0, 1] -> 4 subsets
        int[] arr2 = {0, 0, 1};
        int target2 = 1;
        System.out.println("\nTest 2 (arr=[0,0,1], target=1 with zeros):");
        System.out.println("  Memoization:     " + countSubsetsMemo(arr2, target2)); // 4
        System.out.println("  Tabulation:      " + countSubsetsTabulation(arr2, target2)); // 4
        System.out.println("  Space-Optimized: " + countSubsetsSpaceOptimized(arr2, target2)); // 4
    }
}
