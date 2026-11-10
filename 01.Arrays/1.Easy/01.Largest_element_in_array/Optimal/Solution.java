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
Traverse the array once while maintaining a variable max initialized to arr[0]. Update max whenever a greater element is encountered.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int largest(int arr[], int n) {
        int max = arr[0];
        for (int i = 1; i < n; i++) {
            if (arr[i] > max) max = arr[i];
        }
        return max;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
