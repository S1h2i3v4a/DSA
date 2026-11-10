/*
QUESTION:-
Given a string 's', you've to count the number of distinct substrings (including the empty substring) of the given string.

Example:
Input: s = "ab"
Output: 4
Explanation: Substrings are "", "a", "b", "ab".
*/

/*
APPROACH:-
Trie Data Structure: Insert all suffixes of string s into a Trie. Each newly created node corresponds to a unique distinct substring. Count of distinct substrings = total nodes created + 1.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
    }

    public static int distinctSubsequences(String s) {
        TrieNode root = new TrieNode();
        int count = 0;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            TrieNode curr = root;
            for (int j = i; j < n; j++) {
                int idx = s.charAt(j) - 'a';
                if (curr.children[idx] == null) {
                    curr.children[idx] = new TrieNode();
                    count++;
                }
                curr = curr.children[idx];
            }
        }
        return count + 1;
    }
}

// TIME COMPLEXITY = O(N^2)
// SPACE COMPLEXITY = O(N^2 * 26)
