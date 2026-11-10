/*
QUESTION:-
Given an array arr[] of size N, check if it is sorted in non-decreasing order or not.

Example:
Input: N = 5, arr[] = {10, 20, 30, 40, 50}
Output: true
Explanation: The given array is sorted.
*/

/*
APPROACH:-
Compare all pairs (i, j) where i < j. If any arr[i] > arr[j], return false.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    boolean arraySortedOrNot(int[] arr, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr[i] > arr[j]) return false;
            }
        }
        return true;
    }
}

// TIME COMPLEXITY = O(N^2)
// SPACE COMPLEXITY = O(1)
