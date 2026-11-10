/*
QUESTION:-
Given an array Arr of size N, print second largest distinct element from an array. Return -1 if not found.

Example:
Input: N = 6, Arr[] = {12, 35, 1, 10, 34, 1}
Output: 34
Explanation: The largest element is 35 and the second largest is 34.
*/

/*
APPROACH:-
Sort the array in non-decreasing order. The largest element is at n-1. Iterate backwards from n-2 to 0 to find the first element strictly smaller than largest.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int print2largest(int arr[], int n) {
        Arrays.sort(arr);
        int largest = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] != largest) return arr[i];
        }
        return -1;
    }
}

// TIME COMPLEXITY = O(N log N)
// SPACE COMPLEXITY = O(1)
