/*
QUESTION:
Palindrome Partitioning II (Minimum Cuts).

APPROACH:
- 1D Tabulation with precomputed boolean palindrome table `isPal[n][n]`.
- `dp[i] = min_{i <= j < n}(1 + dp[j + 1])` for all j where `isPal[i][j] == true`.

TIME COMPLEXITY: O(N^2).
SPACE COMPLEXITY: O(N^2) palindrome matrix + O(N) dp array.
*/

class Solution {
    public int minCut(String s) {
        int n = s.length();
        boolean[][] isPal = new boolean[n][n];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (s.charAt(i) == s.charAt(j) && (j - i <= 2 || isPal[i + 1][j - 1])) {
                    isPal[i][j] = true;
                }
            }
        }

        int[] dp = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            int minCost = Integer.MAX_VALUE;
            for (int j = i; j < n; j++) {
                if (isPal[i][j]) {
                    int cost = 1 + dp[j + 1];
                    minCost = Math.min(minCost, cost);
                }
            }
            dp[i] = minCost;
        }

        return dp[0] - 1;
    }
}
