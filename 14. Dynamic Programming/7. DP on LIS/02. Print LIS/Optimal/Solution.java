/*
QUESTION:
Print the longest strictly increasing subsequence.

APPROACH:
- DP with Parent Tracking Array:
- `dp[i]` stores length of LIS ending at index `i`.
- `parent[i]` stores previous index that led to `dp[i]`.
- Backtrack from index of max(dp) to reconstruct the subsequence.

TIME COMPLEXITY: O(N^2).
SPACE COMPLEXITY: O(N).
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Solution {
    public List<Integer> printLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int[] parent = new int[n];
        Arrays.fill(dp, 1);

        for (int i = 0; i < n; i++) parent[i] = i;

        int maxLen = 1;
        int lastIndex = 0;

        for (int i = 0; i < n; i++) {
            for (int prev = 0; prev < i; prev++) {
                if (nums[prev] < nums[i] && 1 + dp[prev] > dp[i]) {
                    dp[i] = 1 + dp[prev];
                    parent[i] = prev;
                }
            }
            if (dp[i] > maxLen) {
                maxLen = dp[i];
                lastIndex = i;
            }
        }

        List<Integer> lis = new ArrayList<>();
        lis.add(nums[lastIndex]);
        while (parent[lastIndex] != lastIndex) {
            lastIndex = parent[lastIndex];
            lis.add(nums[lastIndex]);
        }

        Collections.reverse(lis);
        return lis;
    }
}
