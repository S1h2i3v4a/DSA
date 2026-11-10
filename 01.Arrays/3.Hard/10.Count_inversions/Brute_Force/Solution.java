/*
QUESTION:-
Given an array of integers. Find the Inversion Count in the array. Two array elements arr[i] and arr[j] form an inversion if arr[i] > arr[j] and i < j.

Example:
Input: N = 5, arr[] = {2, 4, 1, 3, 5}
Output: 3
*/

/*
APPROACH:-
Run two nested loops. Outer loop picks arr[i], inner loop checks every arr[j] with j > i. Increment count if arr[i] > arr[j].
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    static long inversionCount(long arr[], int n) {
        long count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr[i] > arr[j]) count++;
            }
        }
        return count;
    }
}

// TIME COMPLEXITY = O(N^2)
// SPACE COMPLEXITY = O(1)
