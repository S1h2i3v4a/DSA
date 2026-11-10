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
Split and Reverse: Trim the string and split by regex whitespace '\\s+'. Iterate over the word array backwards and build the resulting string with spaces.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public String reverseWords(String s) {
        String[] words = s.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            sb.append(words[i]);
            if (i > 0) sb.append(" ");
        }
        return sb.toString();
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(N)
