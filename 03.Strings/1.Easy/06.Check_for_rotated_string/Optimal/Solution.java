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
Concatenation trick: Concatenating s with itself (s + s) contains all possible cyclic rotations of s as substrings. Return true if lengths match and (s + s).contains(goal).
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public boolean rotateString(String s, String goal) {
        return s.length() == goal.length() && (s + s).contains(goal);
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(N)
