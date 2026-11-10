/*
QUESTION:-
Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's. You must do it in place.

Example:
Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
Output: [[1,0,1],[0,0,0],[1,0,1]]
*/

/*
APPROACH:-
Traverse the matrix. When a 0 is found at (i, j), mark all non-zero cells in row i and col j with a temporary marker value (or use extra visited matrix). Finally convert markers to 0.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] copy = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) copy[i][j] = matrix[i][j];
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (copy[i][j] == 0) {
                    for (int c = 0; c < n; c++) matrix[i][c] = 0;
                    for (int r = 0; r < m; r++) matrix[r][j] = 0;
                }
            }
        }
    }
}

// TIME COMPLEXITY = O(M * N * (M + N))
// SPACE COMPLEXITY = O(M * N)
