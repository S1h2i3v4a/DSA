/*
QUESTION:
Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's.

Example 1:
Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
Output: [[1,0,1],[0,0,0],[1,0,1]]

Example 2:
Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]

APPROACH:
To solve this problem in-place, we can follow these steps:
1. Use two boolean variables, firstRowZero and firstColZero, to check if the first row and first column contain zeros initially.
2. Iterate through the matrix and if an element is zero, set the corresponding element in the first row and first column to zero.
3. Iterate through the matrix again, excluding the first row and first column. If an element in the first row or first column is zero, set the current element to zero.
4. Finally, based on the values in firstRowZero and firstColZero, set the first row and first column to zero if needed.

TIME COMPLEXITY: O(m * n), where m and n are the dimensions of the matrix.
SPACE COMPLEXITY: O(1), as we are using constant extra space.

*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public void setZeroes(int[][] matrix) {
        int col0 = 1, rows = matrix.length, cols = matrix[0].length;
        for (int i = 0; i < rows; i++) {
            if (matrix[i][0] == 0) col0 = 0;
            for (int j = 1; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        for (int i = rows - 1; i >= 0; i--) {
            for (int j = cols - 1; j >= 1; j--) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0)
                    matrix[i][j] = 0;
            }
            if (col0 == 0) matrix[i][0] = 0;
        }
    }
}

// TIME COMPLEXITY = O(m * n), where m and n are the dimensions of the matrix.
// SPACE COMPLEXITY = O(1), as we are using constant extra space.
