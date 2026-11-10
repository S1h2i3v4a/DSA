/*
QUESTION:
Given an array arr of size n, partition it into two subsets such that the absolute difference between their sums is minimized.

APPROACH:
- Recursive generation: generate all subset sums, find min |totalSum - 2 * s1|.

TIME COMPLEXITY: O(2^N).
SPACE COMPLEXITY: O(N) recursion stack.
*/

class Solution {
    public int minDifference(int[] arr, int n) {
        int total = 0;
        for (int x : arr) total += x;
        return solve(n - 1, 0, total, arr);
    }

    private int solve(int i, int currentSum, int total, int[] arr) {
        if (i < 0) {
            return Math.abs(total - 2 * currentSum);
        }
        int pick = solve(i - 1, currentSum + arr[i], total, arr);
        int notPick = solve(i - 1, currentSum, total, arr);
        return Math.min(pick, notPick);
    }
}
