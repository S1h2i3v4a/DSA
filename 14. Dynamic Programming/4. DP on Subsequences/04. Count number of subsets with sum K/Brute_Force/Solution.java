/*
QUESTION:
Given an array arr of n integers and an integer target, count all subsets of arr with sum equal to target.

APPROACH:
- Pure recursion: count = count(i - 1, target) + count(i - 1, target - arr[i]).
- Handles zeros appropriately when reaching index 0.

TIME COMPLEXITY: O(2^N).
SPACE COMPLEXITY: O(N) recursion stack.
*/

class Solution {
    private static final int MOD = 1000000007;

    public int findWays(int[] arr, int target) {
        return solve(arr.length - 1, target, arr);
    }

    private int solve(int i, int target, int[] arr) {
        if (i < 0) return target == 0 ? 1 : 0;
        int notPick = solve(i - 1, target, arr);
        int pick = 0;
        if (arr[i] <= target) pick = solve(i - 1, target - arr[i], arr);
        return (notPick + pick) % MOD;
    }
}
