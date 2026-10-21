/**
 * Problem Name: Count Partitions with Given Difference
 * Platform: GeeksforGeeks / Coding Ninjas
 * Difficulty: Medium
 *
 * Time Complexity: O(N * target) where target = (totalSum + d) / 2
 * Space Complexity:
 *   - Memoization: O(N * target) table + O(N) recursion stack
 *   - Tabulation: O(N * target) table
 *   - Space-Optimized DP: O(target) 1D array
 *
 * Approach:
 *   - Partition array into two subsets S1 and S2 such that:
 *     1. sum(S1) >= sum(S2)
 *     2. sum(S1) - sum(S2) = d
 *   - Using algebra:
 *     sum(S1) + sum(S2) = totalSum
 *     sum(S1) - sum(S2) = d
 *     ----------------------
 *     2 * sum(S1) = totalSum + d  =>  sum(S1) = (totalSum + d) / 2
 *   - Feasibility conditions:
 *     1. totalSum >= d (difference cannot exceed total sum)
 *     2. (totalSum + d) must be EVEN (a fractional sum is impossible for integers)
 *     If either is violated, return 0.
 *   - The problem reduces directly to finding the count of subsets with sum = (totalSum + d) / 2.
 */

import java.util.Arrays;

class CountPartitionsWithGivenDifference {

    private static final int MOD = 1000000007;

    public static int countPartitions(int n, int d, int[] arr) {
        int totalSum = 0;
        for (int x : arr) totalSum += x;

        // Validation checks
        if (totalSum - d < 0 || (totalSum + d) % 2 != 0) {
            return 0;
        }

        int target = (totalSum + d) / 2;
        return countSubsetsWithSumK(arr, target);
    }

    // Space-Optimized DP helper for counting subsets with sum K
    private static int countSubsetsWithSumK(int[] arr, int target) {
        int[] dp = new int[target + 1];

        if (arr[0] == 0) {
            dp[0] = 2; // Include or exclude 0
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
        // n = 4, d = 3, arr = [5, 2, 6, 4]
        // totalSum = 17, target = (17 + 3) / 2 = 10.
        // Partitions: {6, 4} and {5, 2} -> difference = 10 - 7 = 3. Count = 1
        int[] arr1 = {5, 2, 6, 4};
        int d1 = 3;
        System.out.println("Test 1 (arr=[5,2,6,4], d=3): " + countPartitions(arr1.length, d1, arr1)); // 1

        // Test Case 2:
        // n = 5, d = 1, arr = [1, 1, 1, 1, 1]
        // totalSum = 5, target = (5 + 1) / 2 = 3.
        // Number of subsets of size 3 from 5 ones: C(5, 3) = 10
        int[] arr2 = {1, 1, 1, 1, 1};
        int d2 = 1;
        System.out.println("Test 2 (arr=[1,1,1,1,1], d=1): " + countPartitions(arr2.length, d2, arr2)); // 10
    }
}
