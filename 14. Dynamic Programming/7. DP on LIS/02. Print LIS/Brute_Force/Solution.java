/*
QUESTION:
Given an array nums, print the longest strictly increasing subsequence.

APPROACH:
- Pure recursion generating all increasing subsequences and finding the longest one.

TIME COMPLEXITY: O(2^N * N).
SPACE COMPLEXITY: O(N) recursion stack.
*/

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> printLIS(int[] nums) {
        List<Integer> best = new ArrayList<>();
        solve(0, new ArrayList<>(), nums, best);
        return best;
    }

    private void solve(int i, List<Integer> cur, int[] nums, List<Integer> best) {
        if (i == nums.length) {
            if (cur.size() > best.size()) {
                best.clear();
                best.addAll(cur);
            }
            return;
        }

        if (cur.isEmpty() || nums[i] > cur.get(cur.size() - 1)) {
            cur.add(nums[i]);
            solve(i + 1, cur, nums, best);
            cur.remove(cur.size() - 1);
        }

        solve(i + 1, cur, nums, best);
    }
}
