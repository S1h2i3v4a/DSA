/*
QUESTION:
Find the nth fibonacci number.

APPROACH:
- Top-Down Dynamic Programming with Memoization.
- Store results of subproblems in a dp array of size n + 1 initialized to -1.
- If already computed, return the cached result.

TIME COMPLEXITY: O(N) as each state from 0 to N is computed once.
SPACE COMPLEXITY: O(N) for dp array and recursion call stack.
*/

import java.util.Arrays;

class Solution {
    private static final int MOD = 1000000007;

    public int nthFibonacci(int n) {
        if (n <= 1) return n;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return memo(n, dp);
    }

    private int memo(int n, int[] dp) {
        if (n <= 1) return n;
        if (dp[n] != -1) return dp[n];
        return dp[n] = (memo(n - 1, dp) + memo(n - 2, dp)) % MOD;
    }
}
