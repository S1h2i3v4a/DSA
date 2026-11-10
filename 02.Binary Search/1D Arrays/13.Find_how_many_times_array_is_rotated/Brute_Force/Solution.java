/*
QUESTION:-
Given an ascending sorted rotated array Arr of distinct integers of size N. The array is right rotated K times. Find the value of K.

Example:
Input: N = 5, Arr[] = {5, 1, 2, 3, 4}
Output: 1
Explanation: The original sorted array is 1 2 3 4 5, rotated 1 time to the right.
*/

/*
APPROACH:-
Linear scan: The number of right rotations equals the index of the minimum element in the array.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    int findKRotation(int arr[], int n) {
        int minIdx = 0;
        for (int i = 1; i < n; i++) {
            if (arr[i] < arr[minIdx]) {
                minIdx = i;
            }
        }
        return minIdx;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
