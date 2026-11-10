/*
QUESTION:
Partition array into two subsets such that the absolute difference of their sums is minimized.

APPROACH:
- 2D DP Table for Subset Sum:
- Compute boolean table `dp[n][total/2 + 1]` determining which subset sums are achievable.
- Find maximum `s1 <= total/2` such that `dp[n-1][s1] == true`. Min diff is `total - 2 * s1`.

TIME COMPLEXITY: O(N * TotalSum).
SPACE COMPLEXITY: O(N * TotalSum).
*/

class Solution {
    public int minDifference(int[] arr, int n) {
        int total = 0;
        for (int x : arr) total += x;
        int target = total / 2;

        boolean[][] dp = new boolean[n][target + 1];
        for (int i = 0; i < n; i++) dp[i][0] = true;
        if (arr[0] <= target) dp[0][arr[0]] = true;

        for (int i = 1; i < n; i++) {
            for (int t = 1; t <= target; t++) {
                boolean notPick = dp[i - 1][t];
                boolean pick = (arr[i] <= t) ? dp[i - 1][t - arr[i]] : false;
                dp[i][t] = notPick || pick;
            }
        }

        int minDiff = Integer.MAX_VALUE;
        for (int s1 = target; s1 >= 0; s1--) {
            if (dp[n - 1][s1]) {
                minDiff = total - 2 * s1;
                break;
            }
        }
        return minDiff;
    }
}
