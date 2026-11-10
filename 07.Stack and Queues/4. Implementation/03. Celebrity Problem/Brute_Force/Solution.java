/*
QUESTION:
A celebrity is a person who is known to everyone else at the party, but doesn't know anyone themselves.
Given a square matrix `mat` of size `N x N` where `mat[i][j] = 1` means person `i` knows person `j`,
find if there is a celebrity. If yes, return their index, otherwise return -1.

APPROACH:
- Compute `inDegree[i]` (how many people know `i`) and `outDegree[i]` (how many people `i` knows).
- A celebrity must have `inDegree == N - 1` and `outDegree == 0`.
- Iterate through each person and check if they satisfy the condition.

TIME COMPLEXITY: O(N^2) inspecting all pairs in matrix.
SPACE COMPLEXITY: O(N) for inDegree and outDegree arrays.
*/

class Solution {
    public static int celebrity(int[][] mat) {
        int n = mat.length;
        int[] inDegree = new int[n];
        int[] outDegree = new int[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && mat[i][j] == 1) {
                    outDegree[i]++;
                    inDegree[j]++;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (inDegree[i] == n - 1 && outDegree[i] == 0) {
                return i;
            }
        }
        return -1;
    }
}
