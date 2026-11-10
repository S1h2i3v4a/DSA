/*
QUESTION:
Given weights wt[] and values val[] of n items, put these items in a knapsack of capacity W to get maximum value.

APPROACH:
- Pure recursion: at item i with remaining capacity w, choose pick or notPick.
- Return max(val[i] + solve(i - 1, w - wt[i]), solve(i - 1, w)).

TIME COMPLEXITY: O(2^N).
SPACE COMPLEXITY: O(N) recursion stack.
*/

class Solution {
    public int knapSack(int W, int[] wt, int[] val, int n) {
        return solve(n - 1, W, wt, val);
    }

    private int solve(int i, int w, int[] wt, int[] val) {
        if (i < 0 || w == 0) return 0;
        int notPick = solve(i - 1, w, wt, val);
        int pick = 0;
        if (wt[i] <= w) pick = val[i] + solve(i - 1, w - wt[i], wt, val);
        return Math.max(notPick, pick);
    }
}
