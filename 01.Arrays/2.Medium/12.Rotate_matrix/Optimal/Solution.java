/*
QUESTION:-
You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise) in-place.

Example:
Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [[7,4,1],[8,5,2],[9,6,3]]
*/

/*
APPROACH:-
In-place rotation: 1. Transpose the matrix (swap matrix[i][j] with matrix[j][i]). 2. Reverse each row of the transposed matrix.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        for (int i = 0; i < n; i++) {
            int left = 0, right = n - 1;
            while (left < right) {
                int temp = matrix[i][left];
                matrix[i][left] = matrix[i][right];
                matrix[i][right] = temp;
                left++;
                right--;
            }
        }
    }
}

// TIME COMPLEXITY = O(N^2)
// SPACE COMPLEXITY = O(1)
