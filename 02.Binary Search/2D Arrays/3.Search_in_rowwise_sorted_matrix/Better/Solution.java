/*
QUESTION:-
Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix. This matrix has the following properties:
1. Integers in each row are sorted in ascending from left to right.
2. Integers in each column are sorted in ascending from top to bottom.

Example:
Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
Output: true
*/

/*
APPROACH:-
Binary Search on each row: For each row, perform binary search to check if the target exists.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length, m = matrix[0].length;
        for (int i = 0; i < n; i++) {
            int low = 0, high = m - 1;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (matrix[i][mid] == target) return true;
                else if (matrix[i][mid] < target) low = mid + 1;
                else high = mid - 1;
            }
        }
        return false;
    }
}

// TIME COMPLEXITY = O(N * log M)
// SPACE COMPLEXITY = O(1)
