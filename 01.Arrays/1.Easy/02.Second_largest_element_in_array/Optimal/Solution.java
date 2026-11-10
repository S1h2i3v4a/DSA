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
Single-pass traversal: Maintain largest and secondLargest. If current element > largest, secondLargest becomes largest and largest becomes current. Else if current element < largest and > secondLargest, update secondLargest.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int print2largest(int arr[], int n) {
        int largest = arr[0], secondLargest = -1;
        for (int i = 1; i < n; i++) {
            if (arr[i] > largest) {
                secondLargest = largest;
                largest = arr[i];
            } else if (arr[i] < largest && arr[i] > secondLargest) {
                secondLargest = arr[i];
            }
        }
        return secondLargest;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
