/*
QUESTION:-
Given an unsorted array of size n of positive integers. One number 'A' from set {1, 2, …n} is missing and one number 'B' occurs twice in array. Find these two numbers.

Example:
Input: [3, 1, 2, 5, 3]
Output: [3, 4] (Repeating: 3, Missing: 4)
*/

/*
APPROACH:-
For every number i from 1 to n, count its occurrences across the array. The one with count == 2 is repeating, count == 0 is missing.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    int[] findTwoElement(int arr[], int n) {
        int repeating = -1, missing = -1;
        for (int i = 1; i <= n; i++) {
            int count = 0;
            for (int x : arr) if (x == i) count++;
            if (count == 2) repeating = i;
            else if (count == 0) missing = i;
            if (repeating != -1 && missing != -1) break;
        }
        return new int[] { repeating, missing };
    }
}

// TIME COMPLEXITY = O(N^2)
// SPACE COMPLEXITY = O(1)
