/*
QUESTION:-
Given two sorted arrays of size n and m respectively, find their union. The union is defined as the sorted array of distinct elements present in either array.

Example:
Input: n = 5, m = 5, arr1[] = {1, 2, 3, 4, 5}, arr2[] = {1, 2, 3, 6, 7}
Output: 1 2 3 4 5 6 7
*/

/*
APPROACH:-
Two-pointer merge technique: Compare elements at pointers i and j. Add the smaller one to result if it is not equal to the last added element. Finally append any remaining unique elements.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public static ArrayList<Integer> findUnion(int arr1[], int arr2[], int n, int m) {
        ArrayList<Integer> union = new ArrayList<>();
        int i = 0, j = 0;
        while (i < n && j < m) {
            if (arr1[i] <= arr2[j]) {
                if (union.isEmpty() || union.get(union.size() - 1) != arr1[i]) union.add(arr1[i]);
                i++;
            } else {
                if (union.isEmpty() || union.get(union.size() - 1) != arr2[j]) union.add(arr2[j]);
                j++;
            }
        }
        while (i < n) {
            if (union.isEmpty() || union.get(union.size() - 1) != arr1[i]) union.add(arr1[i]);
            i++;
        }
        while (j < m) {
            if (union.isEmpty() || union.get(union.size() - 1) != arr2[j]) union.add(arr2[j]);
            j++;
        }
        return union;
    }
}

// TIME COMPLEXITY = O(N + M)
// SPACE COMPLEXITY = O(1)
