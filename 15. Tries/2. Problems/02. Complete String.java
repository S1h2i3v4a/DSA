/*
QUESTION:
Ninja developed a fascination for arrays and strings, so this time his teacher gave him an array of strings 'A' of size 'N'. Each element of this array is a string. A string is called a complete string if every prefix of this string is also present in the array 'A'. Ninja is challenged to find the longest complete string in the array 'A'. If there are multiple strings with the same length, return the lexicographically smallest one, and if no string exists, return "None".

Example:
N = 4
A = ["ab", "abc", "a", "bp"]
Explanation:
- Only prefix of the string "a" is "a", which is present in array 'A'. So, it is one of the possible strings.
- Prefixes of the string "ab" are "a" and "ab", both of which are present in array 'A'. So, it is one of the possible strings.
- Prefixes of the string "bp" are "b" and "bp". "b" is not present in array 'A'. So, it cannot be a valid string.
- Prefixes of the string "abc" are "a", "ab", and "abc", all of which are present in array 'A'. So, it is one of the possible strings.
- We need to find the maximum length string, so "abc" is the required string.

Approach:
- Create a Trie data structure to store the strings in the array 'A'.
- Insert all the strings into the Trie.
- Iterate through each string in array 'A':
  - Check if the string is a valid complete string using the Trie.
  - If it is valid, update the answer string based on the length and lexicographical order.

Complexity Analysis:
- Inserting each string into the Trie takes O(N*K) time and space, where N is the number of strings and K is the average length of strings.
- Validating each string takes O(K) time.
- The overall time complexity is O(N*K) and the space complexity is O(N*K), where N is the number of strings and K is the average length of strings.
*/

import java.io.*;
import java.util.*;

// CODE:-
class LongestStringWithAllPrefixes {

    static class Node {
        Node[] children = new Node[26];
        boolean isEnd = false;
    }

    static class Trie {
        Node root = new Node();

        public void insert(String word) {
            Node node = root;
            for (char ch : word.toCharArray()) {
                int idx = ch - 'a';
                if (node.children[idx] == null) {
                    node.children[idx] = new Node();
                }
                node = node.children[idx];
            }
            node.isEnd = true;
        }

        public boolean checkIfAllPrefixesExist(String word) {
            Node node = root;
            for (char ch : word.toCharArray()) {
                int idx = ch - 'a';
                if (node.children[idx] == null) return false;
                node = node.children[idx];
                if (!node.isEnd) return false; // Prefix missing!
            }
            return true;
        }
    }

    public static String completeString(int n, String[] a) {
        Trie trie = new Trie();
        for (String word : a) {
            trie.insert(word);
        }

        String longest = "";

        for (String word : a) {
            if (trie.checkIfAllPrefixesExist(word)) {
                if (word.length() > longest.length()) {
                    longest = word;
                } else if (word.length() == longest.length() && word.compareTo(longest) < 0) {
                    longest = word;
                }
            }
        }

        return longest.isEmpty() ? "None" : longest;
    }

    public static void main(String[] args) {
        // Test Case 1: words = ["n", "ni", "nin", "ninj", "ninja", "ninga"] -> Expected: "ninja"
        String[] a1 = {"n", "ni", "nin", "ninj", "ninja", "ninga"};
        System.out.println("Test 1: " + completeString(a1.length, a1)); // "ninja"

        // Test Case 2: words = ["ab", "bc"] -> Expected: "None" (Prefix 'a' missing)
        String[] a2 = {"ab", "bc"};
        System.out.println("Test 2: " + completeString(a2.length, a2)); // "None"
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
