/*
QUESTION:-
Given two sorted arrays arr1[] and arr2[] of sizes n and m in non-decreasing order. Merge them in sorted order without using any extra space.

Example:
Input: n = 4, arr1[] = [1 3 5 7], m = 5, arr2[] = [0 2 6 8 9]
Output: arr1[] = [0 1 2 3], arr2[] = [5 6 7 8 9]
*/

/*
APPROACH:-
Gap Method (Shell Sort): Start with gap = ceil((n + m) / 2). Compare elements at distance gap across both arrays, swapping if out of order. Halve gap in each iteration until gap becomes 0.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public static void merge(long arr1[], long arr2[], int n, int m) {
        int len = n + m;
        int gap = (len / 2) + (len % 2);
        while (gap > 0) {
            int left = 0, right = left + gap;
            while (right < len) {
                // arr1 and arr1
                if (left < n && right < n) {
                    swapIfGreater(arr1, arr1, left, right);
                }
                // arr1 and arr2
                else if (left < n && right >= n) {
                    swapIfGreater(arr1, arr2, left, right - n);
                }
                // arr2 and arr2
                else {
                    swapIfGreater(arr2, arr2, left - n, right - n);
                }
                left++;
                right++;
            }
            if (gap == 1) break;
            gap = (gap / 2) + (gap % 2);
        }
    }
    private static void swapIfGreater(long[] a1, long[] a2, int i1, int i2) {
        if (a1[i1] > a2[i2]) {
            long temp = a1[i1];
            a1[i1] = a2[i2];
            a2[i2] = temp;
        }
    }
}

// TIME COMPLEXITY = O((N + M) log(N + M))
// SPACE COMPLEXITY = O(1)
