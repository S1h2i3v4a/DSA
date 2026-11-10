/*
QUESTION:
Longest Bitonic Subsequence.

APPROACH:
- Dual LIS DP:
  - `dp1[i]`: length of LIS ending at index `i` (increasing left-to-right).
  - `dp2[i]`: length of LIS starting at index `i` (decreasing left-to-right / LIS from right-to-left).
- Maximum bitonic length is `max(dp1[i] + dp2[i] - 1)`.

TIME COMPLEXITY: O(N^2).
SPACE COMPLEXITY: O(N) two arrays.
*/

import java.util.Arrays;

class Solution {
    public int longestBitonicSequence(int n, int[] nums) {
        int[] dp1 = new int[n];
        int[] dp2 = new int[n];
        Arrays.fill(dp1, 1);
        Arrays.fill(dp2, 1);

        for (int i = 0; i < n; i++) {
            for (int prev = 0; prev < i; prev++) {
                if (nums[prev] < nums[i] && 1 + dp1[prev] > dp1[i]) {
                    dp1[i] = 1 + dp1[prev];
                }
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int prev = n - 1; prev > i; prev--) {
                if (nums[prev] < nums[i] && 1 + dp2[prev] > dp2[i]) {
                    dp2[i] = 1 + dp2[prev];
                }
            }
        }

        int maxBitonic = 0;
        for (int i = 0; i < n; i++) {
            maxBitonic = Math.max(maxBitonic, dp1[i] + dp2[i] - 1);
        }

        return maxBitonic;
    }
}
