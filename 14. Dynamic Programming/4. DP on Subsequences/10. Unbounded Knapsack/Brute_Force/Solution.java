/*
QUESTION:
Given weights wt[] and values val[] of n items and capacity W, each item can be picked unlimited times. Maximize knapsack value.

APPROACH:
- Pure recursion: at item i, either skip or take again (w - wt[i]).

TIME COMPLEXITY: O(2^W).
SPACE COMPLEXITY: O(W) recursion stack.
*/

class Solution {
    public int knapSack(int N, int W, int[] val, int[] wt) {
        return solve(N - 1, W, val, wt);
    }

    private int solve(int i, int w, int[] val, int[] wt) {
        if (i < 0 || w == 0) return 0;
        int notTake = solve(i - 1, w, val, wt);
        int take = 0;
        if (wt[i] <= w) take = val[i] + solve(i, w - wt[i], val, wt);
        return Math.max(notTake, take);
    }
}
