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
Binary Search on each row: For each row, check if target could lie within its bounds (row[0] <= target <= row[m-1]), then apply binary search on that row.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length, m = matrix[0].length;
        for (int i = 0; i < n; i++) {
            if (matrix[i][0] <= target && target <= matrix[i][m - 1]) {
                int low = 0, high = m - 1;
                while (low <= high) {
                    int mid = low + (high - low) / 2;
                    if (matrix[i][mid] == target) return true;
                    else if (matrix[i][mid] < target) low = mid + 1;
                    else high = mid - 1;
                }
            }
        }
        return false;
    }
}

// TIME COMPLEXITY = O(N + log M)
// SPACE COMPLEXITY = O(1)
