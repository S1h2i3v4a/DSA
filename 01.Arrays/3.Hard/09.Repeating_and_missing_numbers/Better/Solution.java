/*
QUESTION:-
Given an unsorted array of size n of positive integers. One number 'A' from set {1, 2, …n} is missing and one number 'B' occurs twice in array. Find these two numbers.

Example:
Input: [3, 1, 2, 5, 3]
Output: [3, 4] (Repeating: 3, Missing: 4)
*/

/*
APPROACH:-
Use a frequency array of size n + 1. Count occurrences of each number, then find the ones with count == 2 (repeating) and count == 0 (missing).
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    int[] findTwoElement(int arr[], int n) {
        int[] freq = new int[n + 1];
        for (int x : arr) freq[x]++;
        int repeating = -1, missing = -1;
        for (int i = 1; i <= n; i++) {
            if (freq[i] == 2) repeating = i;
            else if (freq[i] == 0) missing = i;
        }
        return new int[] { repeating, missing };
    }
}

// TIME COMPLEXITY = O(2N)
// SPACE COMPLEXITY = O(N)
