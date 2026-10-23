/*QUESTION:
Given an array `a` of integers of length `n`, find the nearest smaller number for every element such that the smaller element is on the left side. If no smaller element is present on the left, print -1.

Example:

Input: n = 3
a = {1, 6, 2}
Output: -1 1 1
Explanation: There is no number at the left of 1. The smaller number than 6 and 2 is 1.

APPROACH:
To find the nearest smaller number on the left for each element, we can utilize a stack. 
We iterate through the array from left to right. For each element, we compare it with the elements in the stack. 
If an element in the stack is greater than or equal to the current element, we pop it from the stack since it cannot be the nearest smaller number on the left. 
The top of the stack at each iteration will hold the nearest smaller number on the left for the corresponding element in the array.

CODE:*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public ArrayList<Integer> prevSmaller(ArrayList<Integer> A) {
        ArrayList<Integer> ans = new ArrayList<>();
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < A.size(); i++) {
            while (!st.isEmpty() && st.peek() >= A.get(i)) {
                st.pop();
            }
            if (st.isEmpty()) ans.add(-1);
            else ans.add(st.peek());
            st.push(A.get(i));
        }
        return ans;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
