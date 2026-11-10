/*
QUESTION:-
Given an input string s, reverse the order of the words.
A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space.
Return a string of the words in reverse order concatenated by a single space.

Example:
Input: s = "the sky is blue"
Output: "blue is sky the" 
*/

/*
APPROACH:-
Two Pointers from Right to Left: Traverse from the end of the string to the beginning. Identify word boundaries without creating large intermediate regex arrays, appending words separated by a single space.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        int i = s.length() - 1;
        while (i >= 0) {
            while (i >= 0 && s.charAt(i) == ' ') i--;
            if (i < 0) break;
            int j = i;
            while (i >= 0 && s.charAt(i) != ' ') i--;
            if (sb.length() > 0) sb.append(" ");
            sb.append(s.substring(i + 1, j + 1));
        }
        return sb.toString();
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(N)
