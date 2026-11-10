/*
QUESTION:-
Given an array A of positive integers. An element of array is a leader if it is greater than or equal to all the elements to its right side. The rightmost element is always a leader.

Example:
Input: n = 6, A[] = {16,17,4,3,5,2}
Output: 17 5 2
*/

/*
APPROACH:-
Traverse from right to left while keeping track of maxFromRight. If current element >= maxFromRight, it is a leader; add it and update maxFromRight. Reverse the list at the end.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    static ArrayList<Integer> leaders(int arr[], int n) {
        ArrayList<Integer> ans = new ArrayList<>();
        int max = arr[n - 1];
        ans.add(max);
        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] >= max) {
                ans.add(arr[i]);
                max = arr[i];
            }
        }
        Collections.reverse(ans);
        return ans;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
