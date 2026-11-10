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
Linear Scan: Find the global maximum element in the entire matrix. The global maximum is guaranteed to be strictly greater than all its neighbors.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int[] findPeakGrid(int[][] mat) {
        int n = mat.length, m = mat[0].length;
        int maxVal = -1, maxR = 0, maxC = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] > maxVal) {
                    maxVal = mat[i][j];
                    maxR = i;
                    maxC = j;
                }
            }
        }
        return new int[]{maxR, maxC};
    }
}

// TIME COMPLEXITY = O(N * M)
// SPACE COMPLEXITY = O(1)
