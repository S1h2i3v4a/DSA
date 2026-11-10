/*
QUESTION:
Given a wooden stick of length n and an array cuts, return minimum total cost to make all cuts.
Cost of each cut is current stick length.

APPROACH:
- Sort cuts, add 0 and n as endpoints.
- Pure recursion: partition at each cut k between i and j.

TIME COMPLEXITY: O(C!) exponential.
SPACE COMPLEXITY: O(C) recursion stack.
*/

import java.util.Arrays;

class Solution {
    public int minCost(int n, int[] cuts) {
        int c = cuts.length;
        int[] sortedCuts = new int[c + 2];
        System.arraycopy(cuts, 0, sortedCuts, 1, c);
        sortedCuts[0] = 0;
        sortedCuts[c + 1] = n;
        Arrays.sort(sortedCuts);
        return solve(1, c, sortedCuts);
    }

    private int solve(int i, int j, int[] cuts) {
        if (i > j) return 0;
        int mini = Integer.MAX_VALUE;

        for (int k = i; k <= j; k++) {
            int cost = (cuts[j + 1] - cuts[i - 1]) + solve(i, k - 1, cuts) + solve(k + 1, j, cuts);
            mini = Math.min(mini, cost);
        }

        return mini;
    }
}
