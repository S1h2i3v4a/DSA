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
Binary Search per row: Since each row is sorted, find the first occurrence of 1 in each row using binary search (lower bound). Count of 1s is m - firstIndex.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    int rowWithMax1s(int arr[][], int n, int m) {
        int maxCount = 0, rowIdx = -1;
        for (int i = 0; i < n; i++) {
            int firstOne = lowerBound(arr[i], m, 1);
            int count = m - firstOne;
            if (count > maxCount) {
                maxCount = count;
                rowIdx = i;
            }
        }
        return rowIdx;
    }

    private int lowerBound(int[] row, int m, int x) {
        int low = 0, high = m - 1, ans = m;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (row[mid] >= x) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }
}

// TIME COMPLEXITY = O(N * log M)
// SPACE COMPLEXITY = O(1)
