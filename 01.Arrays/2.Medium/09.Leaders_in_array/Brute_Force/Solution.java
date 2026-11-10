/*
QUESTION:-
Given an array A of positive integers. An element of array is a leader if it is greater than or equal to all the elements to its right side. The rightmost element is always a leader.

Example:
Input: n = 6, A[] = {16,17,4,3,5,2}
Output: 17 5 2
*/

/*
APPROACH:-
For each element A[i], scan all elements to its right (A[j] for j > i). If no element is greater, A[i] is a leader.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    static ArrayList<Integer> leaders(int arr[], int n) {
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            boolean isLeader = true;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] > arr[i]) {
                    isLeader = false;
                    break;
                }
            }
            if (isLeader) ans.add(arr[i]);
        }
        return ans;
    }
}

// TIME COMPLEXITY = O(N^2)
// SPACE COMPLEXITY = O(1)
