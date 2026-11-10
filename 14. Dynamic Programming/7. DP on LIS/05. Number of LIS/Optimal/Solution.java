/*
QUESTION:
Number of Longest Increasing Subsequences.

APPROACH:
- Maintain two arrays:
  - `len[i]`: length of LIS ending at index `i`.
  - `cnt[i]`: number of LIS of length `len[i]` ending at index `i`.
- If `1 + len[prev] > len[i]`, update `len[i] = 1 + len[prev]` and `cnt[i] = cnt[prev]`.
- If `1 + len[prev] == len[i]`, add `cnt[i] += cnt[prev]`.

TIME COMPLEXITY: O(N^2).
SPACE COMPLEXITY: O(N).
*/

import java.util.Arrays;

class Solution {
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        if (n <= 1) return n;

        int[] len = new int[n];
        int[] cnt = new int[n];
        Arrays.fill(len, 1);
        Arrays.fill(cnt, 1);

        int maxLen = 1;

        for (int i = 0; i < n; i++) {
            for (int prev = 0; prev < i; prev++) {
                if (nums[prev] < nums[i]) {
                    if (len[prev] + 1 > len[i]) {
                        len[i] = len[prev] + 1;
                        cnt[i] = cnt[prev];
                    } else if (len[prev] + 1 == len[i]) {
                        cnt[i] += cnt[prev];
                    }
                }
            }
            maxLen = Math.max(maxLen, len[i]);
        }

        int totalCount = 0;
        for (int i = 0; i < n; i++) {
            if (len[i] == maxLen) {
                totalCount += cnt[i];
            }
        }

        return totalCount;
    }
}
