/*
QUESTION:-
Given two strings s and t, return true if t is an anagram of s, and false otherwise.
An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

Example:
Input: s = "anagram", t = "nagaram"
Output: true
*/

/*
APPROACH:-
Sorting: Convert both strings to char arrays, sort them, and compare for equality.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();
        Arrays.sort(sArr);
        Arrays.sort(tArr);
        return Arrays.equals(sArr, tArr);
    }
}

// TIME COMPLEXITY = O(N log N)
// SPACE COMPLEXITY = O(N)
