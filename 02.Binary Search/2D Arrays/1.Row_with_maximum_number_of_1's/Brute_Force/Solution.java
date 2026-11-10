/*
QUESTION:-
Given a boolean 2D array of n x m dimensions where each row is sorted. Find the 0-based index of the first row that has the maximum number of 1's.

Example:
Input: n = 4, m = 4, arr[][] = {{0, 1, 1, 1}, {0, 0, 1, 1}, {1, 1, 1, 1}, {0, 0, 0, 0}}
Output: 2
Explanation: Row 2 contains 4 1's (0-based indexing).
*/

/*
APPROACH:-
Linear traversal: For each row, count all 1s by iterating through each column. Track the row with the maximum count.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    int rowWithMax1s(int arr[][], int n, int m) {
        int maxCount = 0, rowIdx = -1;
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 1) count++;
            }
            if (count > maxCount) {
                maxCount = count;
                rowIdx = i;
            }
        }
        return rowIdx;
    }
}

// TIME COMPLEXITY = O(N * M)
// SPACE COMPLEXITY = O(1)
