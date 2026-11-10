/*
QUESTION:-
Given two sorted arrays arr1[] and arr2[] of sizes n and m in non-decreasing order. Merge them in sorted order without using any extra space.

Example:
Input: n = 4, arr1[] = [1 3 5 7], m = 5, arr2[] = [0 2 6 8 9]
Output: arr1[] = [0 1 2 3], arr2[] = [5 6 7 8 9]
*/

/*
APPROACH:-
Use a temporary array of size n + m. Copy elements of both arrays, sort the temporary array, then copy back the first n to arr1 and remaining m to arr2.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public static void merge(long arr1[], long arr2[], int n, int m) {
        long[] temp = new long[n + m];
        for (int i = 0; i < n; i++) temp[i] = arr1[i];
        for (int i = 0; i < m; i++) temp[n + i] = arr2[i];
        Arrays.sort(temp);
        for (int i = 0; i < n; i++) arr1[i] = temp[i];
        for (int i = 0; i < m; i++) arr2[i] = temp[n + i];
    }
}

// TIME COMPLEXITY = O((N + M) log(N + M))
// SPACE COMPLEXITY = O(N + M)
