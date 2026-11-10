/*
QUESTION:
Given a set of distinct positive integers nums, return largest subset such that every pair (a, b) satisfies a % b == 0 or b % a == 0.

APPROACH:
- Sort array. Pure recursion generating subsets where nums[i] % prev == 0.

TIME COMPLEXITY: O(2^N).
SPACE COMPLEXITY: O(N) stack.
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
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

        if (cur.isEmpty() || nums[i] % cur.get(cur.size() - 1) == 0) {
            cur.add(nums[i]);
            solve(i + 1, cur, nums, best);
            cur.remove(cur.size() - 1);
        }

        solve(i + 1, cur, nums, best);
    }
}
