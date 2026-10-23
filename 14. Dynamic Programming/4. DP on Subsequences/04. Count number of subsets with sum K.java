/*
QUESTION:
Given an array arr[] of non-negative integers and an integer sum, 
the task is to count all subsets of the given array with a sum 
equal to a given sum.

Note: Answer can be very large, so, output answer modulo 10^9+7

Example 1:
Input: N = 6, arr[] = {2, 3, 5, 6, 8, 10}
       sum = 10
Output: 3
Explanation: {2, 3, 5}, {2, 8}, {10}

APPROACH:
- We'll use a bottom-up dynamic programming approach using a 2D dp array.
- Initialize dp[i][j] as the number of subsets with sum 'j' using the first 'i' elements.
- Base cases:
  - dp[0][0] = 1 since an empty subset has a sum of 0.
  - dp[i][0] = 1 since there's always an empty subset.
- For each element 'arr[i]' and each possible sum 'j' from 1 to 'sum':
  - dp[i][j] = dp[i-1][j] (not taking the current element)
  - if 'arr[i]' is less than or equal to 'j', then dp[i][j] += dp[i-1][j-arr[i]] (taking the current element)
- Return dp[n][sum] which represents the number of subsets with sum 'sum' using all 'n' elements.

COMPLEXITY ANALYSIS:
- The time complexity of the solution is O(n * sum), where 'n' is the number of elements and 'sum' is the required sum.
- The space complexity is O(n * sum) for the dp array.

CODE:
*/

import java.io.*;
import java.util.*;
import java.util.Arrays;

// CODE:-
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

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
