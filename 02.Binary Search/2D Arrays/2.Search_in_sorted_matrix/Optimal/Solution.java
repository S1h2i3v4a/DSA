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
Virtual 1D Binary Search: Flatten the 2D matrix conceptually into a 1D array of size N*M. Index mid corresponds to matrix[mid / M][mid % M]. Perform standard binary search.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length, m = matrix[0].length;
        int low = 0, high = n * m - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int val = matrix[mid / m][mid % m];
            if (val == target) return true;
            else if (val < target) low = mid + 1;
            else high = mid - 1;
        }
        return false;
    }
}

// TIME COMPLEXITY = O(log(N * M))
// SPACE COMPLEXITY = O(1)
