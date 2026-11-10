/*
QUESTION:-
Given an array nums of size n, return the majority element. The majority element is the element that appears more than floor(n / 2) times.

Example:
Input: nums = [3,2,3]
Output: 3
*/

/*
APPROACH:-
Boyer-Moore Voting Algorithm: Maintain a candidate and a count. When count == 0, pick current element as candidate. Increment count if current == candidate, else decrement count.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int majorityElement(int[] nums) {
        int candidate = 0, count = 0;
        for (int x : nums) {
            if (count == 0) candidate = x;
            count += (x == candidate) ? 1 : -1;
        }
        return candidate;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
