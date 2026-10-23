/*
QUESTION:-

Given an array A of positive integers. Your task is to find the leaders in the array. An element of the array is a leader if it is greater than or equal to all the elements to its right side. The rightmost element is always a leader.

Example 1:
Input:
n = 6
A[] = {16,17,4,3,5,2}
Output: 17 5 2
Explanation: The first leader is 17 as it is greater than all the elements to its right. Similarly, the next leader is 5. The rightmost element is always a leader, so it is also included.

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
                max = arr[i];
                ans.add(max);
            }
        }
        Collections.reverse(ans);
        return ans;
    }
}

// TIME COMPLEXITY = O(n), where n is the size of the array.
// SPACE COMPLEXITY = O(1)
