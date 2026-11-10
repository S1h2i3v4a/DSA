/*
QUESTION:-
Given an array A[] of size n. The task is to find the largest element in it.

Example:
Input: n = 5, A[] = {1, 8, 7, 56, 90}
Output: 90
Explanation: The largest element of given array is 90.
*/

/*
APPROACH:-
Sort the array in non-decreasing order. The largest element will be at index n - 1.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int largest(int arr[], int n) {
        Arrays.sort(arr);
        return arr[n - 1];
    }
}

// TIME COMPLEXITY = O(N log N)
// SPACE COMPLEXITY = O(1)
