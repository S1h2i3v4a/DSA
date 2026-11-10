/*
QUESTION:-
Given a row-wise sorted matrix of size R x C where R and C are always odd, find the median of the matrix.

Example:
Input: R = 3, C = 3, matrix = [[1, 3, 5], [2, 6, 9], [3, 6, 9]]
Output: 5
Explanation: Sorted elements are [1, 2, 3, 3, 5, 6, 6, 9, 9]. Median is 5.
*/

/*
APPROACH:-
Binary Search on Range: Find the minimum and maximum values in the matrix. Median is the value having at least (R*C+1)/2 elements smaller than or equal to it. Use binary search on the value range [min, max], and for each mid, count elements <= mid across all rows using upper_bound.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    int median(int matrix[][], int R, int C) {
        int low = Integer.MAX_VALUE, high = Integer.MIN_VALUE;
        for (int i = 0; i < R; i++) {
            low = Math.min(low, matrix[i][0]);
            high = Math.max(high, matrix[i][C - 1]);
        }
        int req = (R * C + 1) / 2;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int count = 0;
            for (int i = 0; i < R; i++) {
                count += countSmallerOrEqual(matrix[i], mid);
            }
            if (count < req) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    private int countSmallerOrEqual(int[] row, int mid) {
        int l = 0, h = row.length - 1;
        while (l <= h) {
            int m = l + (h - l) / 2;
            if (row[m] <= mid) l = m + 1;
            else h = m - 1;
        }
        return l;
    }
}

// TIME COMPLEXITY = O(32 * R * log C)
// SPACE COMPLEXITY = O(1)
