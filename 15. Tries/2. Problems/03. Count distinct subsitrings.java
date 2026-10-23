/*
QUESTION:
Given a string of length N of lowercase alphabet characters. Implement the function countDistinctSubstring() to return the count of total distinct substrings of the given string.

Approach:
- Create a Trie data structure to efficiently store and count distinct substrings.
- For each character in the string, insert its suffixes into the Trie, and each insertion increases the count of distinct substrings.
- Return the total count of distinct substrings.

Complexity Analysis:
- Inserting each substring into the Trie takes O(N) time and O(N) space.
- Overall time complexity is O(N^2) since there are O(N^2) substrings in total.
- Space complexity is O(N) to store the Trie.
*/

import java.io.*;
import java.util.*;

// CODE:-
class NumberOfDistinctSubstringsInAString {

    static class Node {
        Node[] children = new Node[26];
    }

    public static int countDistinctSubstrings(String s) {
        Node root = new Node();
        int count = 0;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            Node node = root;
            for (int j = i; j < n; j++) {
                int idx = s.charAt(j) - 'a';
                if (node.children[idx] == null) {
                    node.children[idx] = new Node();
                    count++; // Found a new unique substring!
                }
                node = node.children[idx];
            }
        }

        // Return count (distinct non-empty substrings)
        // If empty substring is counted, return count + 1
        return count + 1;
    }

    public static void main(String[] args) {
        // Test Case 1: s = "abab" -> Distinct substrings: "", "a", "b", "ab", "ba", "aba", "bab", "abab" = 8
        System.out.println("Test 1 (\"abab\"): " + countDistinctSubstrings("abab")); // 8

        // Test Case 2: s = "sds" -> Distinct substrings: "", "s", "d", "sd", "ds", "sds" = 6
        System.out.println("Test 2 (\"sds\"): " + countDistinctSubstrings("sds")); // 6
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
