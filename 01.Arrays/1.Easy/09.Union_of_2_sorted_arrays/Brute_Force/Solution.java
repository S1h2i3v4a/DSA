/*
QUESTION:-
Given two sorted arrays of size n and m respectively, find their union. The union is defined as the sorted array of distinct elements present in either array.

Example:
Input: n = 5, m = 5, arr1[] = {1, 2, 3, 4, 5}, arr2[] = {1, 2, 3, 6, 7}
Output: 1 2 3 4 5 6 7
*/

/*
APPROACH:-
Insert all elements from both arrays into a TreeSet to eliminate duplicates and keep them ordered. Convert set to an ArrayList.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public static ArrayList<Integer> findUnion(int arr1[], int arr2[], int n, int m) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < n; i++) set.add(arr1[i]);
        for (int j = 0; j < m; j++) set.add(arr2[j]);
        return new ArrayList<>(set);
    }
}

// TIME COMPLEXITY = O((N + M) log(N + M))
// SPACE COMPLEXITY = O(N + M)
