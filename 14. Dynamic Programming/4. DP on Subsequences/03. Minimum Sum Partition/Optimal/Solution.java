/*
QUESTION:
Partition array into two subsets such that the absolute difference of their sums is minimized.

APPROACH:
- Space-Optimized 1D DP:
- Maintain 1D boolean array of size `total / 2 + 1`.
- Update backwards: `dp[t] = dp[t] || dp[t - arr[i]]`.

TIME COMPLEXITY: O(N * TotalSum).
SPACE COMPLEXITY: O(TotalSum).
*/

class Solution {
    public int minDifference(int[] arr, int n) {
        int total = 0;
        for (int x : arr) total += x;
        int target = total / 2;

        boolean[] dp = new boolean[target + 1];
        dp[0] = true;

        for (int num : arr) {
            for (int t = target; t >= num; t--) {
                dp[t] = dp[t] || dp[t - num];
            }
        }

        for (int s1 = target; s1 >= 0; s1--) {
            if (dp[s1]) {
                return total - 2 * s1;
            }
        }
        return 0;
    }
}
