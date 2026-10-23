/**
 * Problem Name: Minimum Cost to Cut a Stick
 * Platform: LeetCode (LC 1547) / GeeksforGeeks
 * Difficulty: Hard
 *
 * Time Complexity: O(C^3) where C = cuts.length
 * Space Complexity: O(C^2) for DP table
 *
 * Approach:
 *   - Given a wooden stick of length n and an array `cuts` where cuts[i] represents a cut position.
 *   - The cost of one cut is the length of the stick to be cut.
 *   - We want to minimize total cost across all cuts.
 *   - Partition DP Formulation:
 *     1. Pad `cuts` with 0 at index 0 and `n` at the end, then SORT the array.
 *     2. Let f(i, j) be the minimum cost to perform cuts from index i to j:
 *        For any chosen first cut k in [i, j]:
 *        Cost = (cuts[j + 1] - cuts[i - 1]) + f(i, k - 1) + f(k + 1, j).
 *        Minimizing over all k in [i, j] gives the optimal cost.
 */

import java.util.Arrays;

class MinimumCostToCutAStick {

    public static int minCost(int n, int[] cuts) {
        int c = cuts.length;
        int[] sortedCuts = new int[c + 2];
        System.arraycopy(cuts, 0, sortedCuts, 1, c);
        sortedCuts[0] = 0;
        sortedCuts[c + 1] = n;
        Arrays.sort(sortedCuts);

        int[][] dp = new int[c + 2][c + 2];

        for (int i = c; i >= 1; i--) {
            for (int j = i; j <= c; j++) {
                int minCost = Integer.MAX_VALUE;
                for (int k = i; k <= j; k++) {
                    int cost = (sortedCuts[j + 1] - sortedCuts[i - 1])
                             + dp[i][k - 1]
                             + dp[k + 1][j];
                    minCost = Math.min(minCost, cost);
                }
                dp[i][j] = minCost;
            }
        }

        return dp[1][c];
    }

    public static void main(String[] args) {
        // Test Case 1: n = 7, cuts = [1,3,4,5] -> Expected: 16
        int[] cuts1 = {1, 3, 4, 5};
        System.out.println("Test 1 (n=7, cuts=[1,3,4,5]): " + minCost(7, cuts1)); // 16

        // Test Case 2: n = 9, cuts = [5,6,1,4,2] -> Expected: 22
        int[] cuts2 = {5, 6, 1, 4, 2};
        System.out.println("Test 2 (n=9, cuts=[5,6,1,4,2]): " + minCost(9, cuts2)); // 22
    }
}
