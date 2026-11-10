/*
QUESTION:
Given a sequence of matrices, find the most efficient way to multiply these matrices together.
Dimension array arr[] of size n represents matrices A1 to A_{n-1} where matrix Ai has dimensions arr[i-1] x arr[i].

APPROACH:
- Pure recursion: partition at each k from i to j-1.
- solve(i, j) = min_{i <= k < j}(solve(i, k) + solve(k+1, j) + arr[i-1] * arr[k] * arr[j]).

TIME COMPLEXITY: O(2^N) exponential Catalan number.
SPACE COMPLEXITY: O(N) recursion stack.
*/

class Solution {
    public int matrixMultiplication(int[] arr, int n) {
        return solve(1, n - 1, arr);
    }

    private int solve(int i, int j, int[] arr) {
        if (i == j) return 0;
        int mini = Integer.MAX_VALUE;

        for (int k = i; k < j; k++) {
            int cost = solve(i, k, arr) + solve(k + 1, j, arr) + arr[i - 1] * arr[k] * arr[j];
            mini = Math.min(mini, cost);
        }

        return mini;
    }
}
