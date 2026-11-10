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
Two Pointers Simulation: Simulate merging without storing the combined array until the k-th element is reached.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public long kthElement(int k, int arr1[], int arr2[]) {
        int n1 = arr1.length, n2 = arr2.length;
        int i = 0, j = 0, cnt = 0;
        long ans = -1;

        while (i < n1 && j < n2) {
            cnt++;
            if (arr1[i] <= arr2[j]) {
                if (cnt == k) return arr1[i];
                i++;
            } else {
                if (cnt == k) return arr2[j];
                j++;
            }
        }
        while (i < n1) {
            cnt++;
            if (cnt == k) return arr1[i];
            i++;
        }
        while (j < n2) {
            cnt++;
            if (cnt == k) return arr2[j];
            j++;
        }
        return -1;
    }
}

// TIME COMPLEXITY = O(K)
// SPACE COMPLEXITY = O(1)
