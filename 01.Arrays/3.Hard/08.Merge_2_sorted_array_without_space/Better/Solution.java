/*
QUESTION:-
Given two sorted arrays arr1[] and arr2[] of sizes n and m in non-decreasing order. Merge them in sorted order without using any extra space.

Example:
Input: n = 4, arr1[] = [1 3 5 7], m = 5, arr2[] = [0 2 6 8 9]
Output: arr1[] = [0 1 2 3], arr2[] = [5 6 7 8 9]
*/

/*
APPROACH:-
Two-pointer swap: Pointer left starts at end of arr1 (n - 1) and right starts at beginning of arr2 (0). If arr1[left] > arr2[right], swap and left--, right++. Once done, sort arr1 and arr2 individually.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public static void merge(long arr1[], long arr2[], int n, int m) {
        int left = n - 1, right = 0;
        while (left >= 0 && right < m) {
            if (arr1[left] > arr2[right]) {
                long temp = arr1[left];
                arr1[left] = arr2[right];
                arr2[right] = temp;
                left--;
                right++;
            } else {
                break;
            }
        }
        Arrays.sort(arr1);
        Arrays.sort(arr2);
    }
}

// TIME COMPLEXITY = O(min(N, M) + N log N + M log M)
// SPACE COMPLEXITY = O(1)
