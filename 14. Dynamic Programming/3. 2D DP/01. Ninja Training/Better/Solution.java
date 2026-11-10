/*
QUESTION:
Ninja plans 3 activities per day over n days. Cannot do the same activity on consecutive days.
Find the maximum merit points Ninja can earn.

APPROACH:
- Memoization:
- Maintain 2D memo table `dp[n][4]` where dp[day][last] stores maximum points from day 0 to day.

TIME COMPLEXITY: O(N * 4 * 3) = O(N).
SPACE COMPLEXITY: O(N * 4) table + O(N) recursion stack.
*/

import java.util.Arrays;

class Solution {
    public int maximumPoints(int[][] points, int n) {
        int[][] dp = new int[n][4];
        for (int[] row : dp) Arrays.fill(row, -1);
        return solve(n - 1, 3, points, dp);
    }

    private int solve(int day, int last, int[][] points, int[][] dp) {
        if (day == 0) {
            int maxi = 0;
            for (int task = 0; task < 3; task++) {
                if (task != last) {
                    maxi = Math.max(maxi, points[0][task]);
                }
            }
            return maxi;
        }

        if (dp[day][last] != -1) return dp[day][last];

        int maxi = 0;
        for (int task = 0; task < 3; task++) {
            if (task != last) {
                int point = points[day][task] + solve(day - 1, task, points, dp);
                maxi = Math.max(maxi, point);
            }
        }
        return dp[day][last] = maxi;
    }
}
