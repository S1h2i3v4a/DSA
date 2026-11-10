/*
QUESTION:-
Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's. You must do it in place.

Example:
Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
Output: [[1,0,1],[0,0,0],[1,0,1]]
*/

/*
APPROACH:-
Maintain two marker arrays: row[m] and col[n]. In first pass, if matrix[i][j] == 0, mark row[i] = true and col[j] = true. In second pass, set matrix[i][j] = 0 if row[i] or col[j] is marked.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean[] row = new boolean[m];
        boolean[] col = new boolean[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = true;
                    col[j] = true;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (row[i] || col[j]) matrix[i][j] = 0;
            }
        }
    }
}

// TIME COMPLEXITY = O(2 * M * N)
// SPACE COMPLEXITY = O(M + N)
