/*
QUESTION:
Given an array arr of n integers and difference d, partition into two subsets S1 and S2 such that sum(S1) - sum(S2) = d.

APPROACH:
- Let sum(S1) = s1, sum(S2) = s2. s1 + s2 = totalSum, s1 - s2 = d => s1 = (totalSum + d) / 2.
- If totalSum + d is odd or totalSum < d, return 0.
- Pure recursive subset sum count for s1.

TIME COMPLEXITY: O(2^N).
SPACE COMPLEXITY: O(N) recursion stack.
*/

class Solution {
    private static final int MOD = 1000000007;

    public int countPartitions(int n, int d, int[] arr) {
        int total = 0;
        for (int x : arr) total += x;
        if (total - d < 0 || (total - d) % 2 != 0) return 0;
        int target = (total - d) / 2;
        return solve(n - 1, target, arr);
    }

    private int solve(int i, int target, int[] arr) {
        if (i < 0) return target == 0 ? 1 : 0;
        int notPick = solve(i - 1, target, arr);
        int pick = 0;
        if (arr[i] <= target) pick = solve(i - 1, target - arr[i], arr);
        return (notPick + pick) % MOD;
    }
}
