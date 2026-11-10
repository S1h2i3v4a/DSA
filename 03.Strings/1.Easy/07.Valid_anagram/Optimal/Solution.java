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
Frequency Count Array: Use an array of size 26. Increment frequency for characters in s and decrement for characters in t. If all counts are zero, return true.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
            count[t.charAt(i) - 'a']--;
        }
        for (int c : count) {
            if (c != 0) return false;
        }
        return true;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
