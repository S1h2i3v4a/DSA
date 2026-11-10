/*
QUESTION:-
You are given an m x n integer matrix matrix with the following two properties:
1. Each row is sorted in non-decreasing order.
2. The first integer of each row is greater than the last integer of the previous row.
Given an integer target, return true if target is in matrix or false otherwise.

Example:
Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
Output: true
*/

/*
APPROACH:-
Linear Scan: Check every element in the matrix row by row.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length, m = matrix[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == target) return true;
            }
        }
        return false;
    }
}

// TIME COMPLEXITY = O(N * M)
// SPACE COMPLEXITY = O(1)
