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
Single linear pass: An array is sorted in non-decreasing order if and only if each element arr[i] >= arr[i - 1] for all 1 <= i < n.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    boolean arraySortedOrNot(int[] arr, int n) {
        for (int i = 1; i < n; i++) {
            if (arr[i] < arr[i - 1]) return false;
        }
        return true;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
