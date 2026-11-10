/*
QUESTION:-
Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's. You must do it in place.

Example:
Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
Output: [[1,0,1],[0,0,0],[1,0,1]]
*/

/*
APPROACH:-
In-place marking: Use the first row matrix[0][..] and first column matrix[..][0] as markers. Use a boolean col0 to track if the first column itself should be zeroed.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean col0 = false;
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) col0 = true;
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 1; j--) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
            if (col0) matrix[i][0] = 0;
        }
    }
}

// TIME COMPLEXITY = O(2 * M * N)
// SPACE COMPLEXITY = O(1)
