/*
QUESTION:-
Given two strings s and goal, return true if and only if s can become goal after some number of shifts on s.
A shift on s consists of moving the leftmost character of s to the rightmost position.

Example:
Input: s = "abcde", goal = "cdeab"
Output: true
*/

/*
APPROACH:-
Simulate all rotations: Try all n cyclic shifts of string s. In each shift, rotate s by 1 character and check if it equals goal.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public boolean rotateString(String s, String goal) {
        if (s.length() != goal.length()) return false;
        if (s.equals(goal)) return true;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            s = s.substring(1) + s.charAt(0);
            if (s.equals(goal)) return true;
        }
        return false;
    }
}

// TIME COMPLEXITY = O(N^2)
// SPACE COMPLEXITY = O(N)
