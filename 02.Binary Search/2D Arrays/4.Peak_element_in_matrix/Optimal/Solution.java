/*
QUESTION:-
A peak element in a 2D grid is an element that is strictly greater than all of its adjacent neighbors to the left, right, top, and bottom.
Given a 0-indexed m x n matrix mat where no two adjacent cells are equal, find any peak element mat[i][j] and return the length 2 array [i, j].

Example:
Input: mat = [[1,4],[3,2]]
Output: [0,1]
Explanation: Both 3 and 4 are peak elements. Return [0, 1] or [1, 0].
*/

/*
APPROACH:-
Binary Search on Columns: Choose the middle column mid = low + (high - low)/2. Find the row with the maximum element in this column. Compare it with its left and right neighbors. If it's greater than both, it's a peak grid cell. If left neighbor is larger, search left half (high = mid - 1); else search right half (low = mid + 1).
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int[] findPeakGrid(int[][] mat) {
        int n = mat.length, m = mat[0].length;
        int low = 0, high = m - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int maxRow = 0;
            for (int i = 0; i < n; i++) {
                if (mat[i][mid] > mat[maxRow][mid]) {
                    maxRow = i;
                }
            }
            int left = (mid > 0) ? mat[maxRow][mid - 1] : -1;
            int right = (mid < m - 1) ? mat[maxRow][mid + 1] : -1;

            if (mat[maxRow][mid] > left && mat[maxRow][mid] > right) {
                return new int[]{maxRow, mid};
            } else if (left > mat[maxRow][mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return new int[]{-1, -1};
    }
}

// TIME COMPLEXITY = O(N * log M)
// SPACE COMPLEXITY = O(1)
