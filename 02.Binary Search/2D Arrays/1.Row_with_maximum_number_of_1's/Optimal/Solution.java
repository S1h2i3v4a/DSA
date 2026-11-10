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
Staircase Walk: Start from top-right corner (row 0, col m-1). If current cell is 1, move left (col--) to see if more 1s exist in this row, updating best row. If 0, move down (row++).
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    int rowWithMax1s(int arr[][], int n, int m) {
        int r = 0, c = m - 1;
        int maxRow = -1;
        while (r < n && c >= 0) {
            if (arr[r][c] == 1) {
                maxRow = r;
                c--;
            } else {
                r++;
            }
        }
        return maxRow;
    }
}

// TIME COMPLEXITY = O(N + M)
// SPACE COMPLEXITY = O(1)
