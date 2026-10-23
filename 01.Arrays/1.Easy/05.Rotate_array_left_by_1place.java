/*
QUESTION:-
Given an array "ARR' containing 'N' elements, rotate this array Left by once means to shift all elements by one place to the left and move the first element to the last position in the array.

Example:
Input: 'N' 5, 'ARR' = [1, 2, 3, 4, 5]
Output: [2, 3, 4, 5, 1]

Explanation:
We moved the 2nd element to the 1st position and 3rd element to the 2nd position and 4th element to the 3rd position and 5th element to the 4th position and move oth element to the 5th position.
*/

/*
APPROACH:-
-> By observing we can the ans is the arr where arr[i] = arr[i+1] and at last place we will have arr[0]
-> Before traversing store the arr[0] in temp and then traverse the array and make arr[i] = arr[i+1]
-> Make arr[n-1] = arr[0], where n is the size of the array
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public void rotate(int[] arr, int n) {
        int temp = arr[0];
        for (int i = 1; i < n; i++) {
            arr[i - 1] = arr[i];
        }
        arr[n - 1] = temp;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(0)
