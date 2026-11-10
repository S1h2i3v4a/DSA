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
Two-pass traversal: First pass finds the largest element. Second pass finds the maximum element strictly less than largest.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int print2largest(int arr[], int n) {
        int largest = arr[0];
        for (int i = 1; i < n; i++) {
            if (arr[i] > largest) largest = arr[i];
        }
        int secondLargest = -1;
        for (int i = 0; i < n; i++) {
            if (arr[i] > secondLargest && arr[i] != largest) {
                secondLargest = arr[i];
            }
        }
        return secondLargest;
    }
}

// TIME COMPLEXITY = O(2N)
// SPACE COMPLEXITY = O(1)
