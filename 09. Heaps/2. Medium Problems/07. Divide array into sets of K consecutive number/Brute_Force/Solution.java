/*
QUESTION:
Given an array of integers `nums` and a positive integer `k`, check whether it is possible to divide this array into sets of `k` consecutive numbers.

APPROACH:
- Sort `nums`.
- Greedily pick the smallest available number `x` and look for `x, x + 1, ..., x + k - 1`.
- Mark used numbers using a boolean array.
- If any required number is missing, return false.

TIME COMPLEXITY: O(N^2) in worst case due to linear scans for each consecutive number.
SPACE COMPLEXITY: O(N) for visited array.
*/

import java.util.Arrays;

class Solution {
    public boolean isPossibleDivide(int[] nums, int k) {
        if (nums.length % k != 0) return false;
        Arrays.sort(nums);
        int n = nums.length;
        boolean[] used = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (used[i]) continue;
            int curr = nums[i];
            used[i] = true;
            int count = 1;

            for (int j = i + 1; j < n && count < k; j++) {
                if (!used[j] && nums[j] == curr + 1) {
                    used[j] = true;
                    curr = nums[j];
                    count++;
                }
            }
            if (count != k) return false;
        }
        return true;
    }
}
