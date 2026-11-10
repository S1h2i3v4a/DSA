/*
QUESTION:-
Given two sorted arrays arr1 and arr2 of size N and M respectively and an element K.
Find the element that would be at the kth position of the final sorted array.

Example:
Input: arr1[] = {2, 3, 6, 7, 9}, arr2[] = {1, 4, 8, 10}, k = 5
Output: 6
Explanation: The final sorted array would be [1, 2, 3, 4, 6, 7, 8, 9, 10]. The 5th element is 6.
*/

/*
APPROACH:-
Merge into sorted array: Merge arr1 and arr2 into a single array of size N + M. Return the element at index k - 1.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public long kthElement(int k, int arr1[], int arr2[]) {
        int n1 = arr1.length, n2 = arr2.length;
        int[] merged = new int[n1 + n2];
        int i = 0, j = 0, idx = 0;
        while (i < n1 && j < n2) {
            if (arr1[i] <= arr2[j]) merged[idx++] = arr1[i++];
            else merged[idx++] = arr2[j++];
        }
        while (i < n1) merged[idx++] = arr1[i++];
        while (j < n2) merged[idx++] = arr2[j++];
        return merged[k - 1];
    }
}

// TIME COMPLEXITY = O(N + M)
// SPACE COMPLEXITY = O(N + M)
