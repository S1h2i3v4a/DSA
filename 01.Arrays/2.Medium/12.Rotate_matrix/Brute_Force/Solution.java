/*
QUESTION:-
You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise) in-place.

Example:
Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [[7,4,1],[8,5,2],[9,6,3]]
*/

/*
APPROACH:-
Create a dummy matrix ans of size n x n. Place matrix[i][j] at ans[j][n - 1 - i], then copy ans back into matrix.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        int[][] ans = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ans[j][n - 1 - i] = matrix[i][j];
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) matrix[i][j] = ans[i][j];
        }
    }
}

// TIME COMPLEXITY = O(N^2)
// SPACE COMPLEXITY = O(N^2)
