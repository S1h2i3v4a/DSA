/*
QUESTION:
Largest Divisible Subset.

APPROACH:
- Sort array so that divisibility only needs to be checked one-way (`nums[i] % nums[prev] == 0`).
- Use LIS-style DP with `parent` array to track predecessors and backtrack.

TIME COMPLEXITY: O(N^2).
SPACE COMPLEXITY: O(N).
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        int n = nums.length;
        if (n == 0) return new ArrayList<>();

        Arrays.sort(nums);
        int[] dp = new int[n];
        int[] parent = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 0; i < n; i++) parent[i] = i;

        int maxLen = 1;
        int lastIndex = 0;

        for (int i = 0; i < n; i++) {
            for (int prev = 0; prev < i; prev++) {
                if (nums[i] % nums[prev] == 0 && 1 + dp[prev] > dp[i]) {
                    dp[i] = 1 + dp[prev];
                    parent[i] = prev;
                }
            }
            if (dp[i] > maxLen) {
                maxLen = dp[i];
                lastIndex = i;
            }
        }

        List<Integer> ans = new ArrayList<>();
        ans.add(nums[lastIndex]);
        while (parent[lastIndex] != lastIndex) {
            lastIndex = parent[lastIndex];
            ans.add(nums[lastIndex]);
        }

        Collections.reverse(ans);
        return ans;
    }
}
