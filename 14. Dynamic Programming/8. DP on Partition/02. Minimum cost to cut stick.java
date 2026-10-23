/*
QUESTION:
Given a wooden stick of length n units. The stick is labelled from 0 to n.
Given an integer array cuts where cuts[i] denotes a position you should perform a cut at.
You should perform the cuts in order, you can change the order of the cuts as you wish.
The cost of one cut is the length of the stick to be cut, the total cost is the sum of costs of all cuts.
Return the minimum total cost of the cuts.

Example:
Input: n = 7, cuts = [1,3,4,5]
Output: 16
Explanation: Using cuts [1, 3, 4, 5] as the order, the total cost is 1 + 3 + 3 + 4 + 5 = 16.

APPROACH:
- This problem can be solved using dynamic programming.
- Define a 2D DP array `dp` where dp[i][j] represents the minimum total cost to cut the stick between cuts[i] and cuts[j].
- Initialize dp[i][i] to 0 because there is no cost for a single cut.
- Iterate over the stick lengths from length 2 to the length of cuts + 1.
- For each stick length, iterate through all possible cut starting points (i).
- Calculate the cost for cutting the stick between cuts[i] and cuts[i + len - 1] and update the dp array.
- Finally, return dp[0][cuts.size() - 1], which represents the minimum total cost of the cuts.

COMPLEXITY ANALYSIS:
- The time complexity of this approach is O(n^3), where n is the length of the cuts array.
- The space complexity is O(n^2) due to the dp array.
- Overall, the algorithm runs in O(n^3) time and O(n^2) space.

CODE:
*/

import java.io.*;
import java.util.*;
import java.util.Arrays;

// CODE:-
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

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
