/*
QUESTION:-
Union of two arrays can be defined as the common and distinct elements in the two arrays.
Given two sorted arrays of size n and m respectively, find their union.


Example 1:

Input:
n = 5, arr1[] = {1, 2, 3, 4, 5}
m = 3, arr2 [] = {1, 2, 3}
Output: 1 2 3 4 5
Explanation: Distinct elements including
both the arrays are: 1 2 3 4 5.


Example 2:

Input:
n = 5, arr1[] = {2, 2, 3, 4, 5}
m = 5, arr2[] = {1, 1, 2, 3, 4}
Output: 1 2 3 4 5
Explanation: Distinct elements including
both the arrays are: 1 2 3 4 5.
*/

/*
APPROACH:-
-> Take two pointer i and j where i is for arr1 and j is for arr2 and traverse
-> While travsersing 3 cases arises
    -> arr1[ i ] == arr2[ j ]
        Here we found a common element, so insert only one element in the union.
        Let’s insert arr[i] in union and whenever we insert element we increment pointer while pointer is not equal to the inserted element
    -> arr1[i]<arr2[j]
        Here insert arr[i]
    -> arr1[i]>arr2[j]
        Here insert arr2[j]
-> Now check if elements of any array is left to traverse then traverse that array
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public static ArrayList<Integer> findUnion(int arr1[], int arr2[], int n, int m) {
        int i = 0, j = 0;
        ArrayList<Integer> union = new ArrayList<>();
        while (i < n && j < m) {
            if (arr1[i] <= arr2[j]) {
                if (union.size() == 0 || union.get(union.size() - 1) != arr1[i])
                    union.add(arr1[i]);
                i++;
            } else {
                if (union.size() == 0 || union.get(union.size() - 1) != arr2[j])
                    union.add(arr2[j]);
                j++;
            }
        }
        while (i < n) {
            if (union.get(union.size() - 1) != arr1[i])
                union.add(arr1[i]);
            i++;
        }
        while (j < m) {
            if (union.get(union.size() - 1) != arr2[j])
                union.add(arr2[j]);
            j++;
        }
        return union;
    }
}

// TIME COMPLEXITY = O(N+M)
// SPACE COMPLEXITY = O(0)
