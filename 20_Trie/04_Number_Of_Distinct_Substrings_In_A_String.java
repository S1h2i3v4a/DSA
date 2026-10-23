/**
 * Problem Name: Number of Distinct Substrings in a String
 * Platform: GeeksforGeeks / Coding Ninjas
 * Difficulty: Medium
 *
 * Time Complexity: O(N^2) where N is string length
 * Space Complexity: O(N^2 * 26) in worst case for Trie nodes
 *
 * Approach:
 *   - Find the total count of distinct substrings of a given string `s`.
 *   - Trie-Based Insight:
 *     Every distinct substring of `s` is a prefix of some suffix of `s`.
 *     Therefore, every unique node inserted into a Trie while inserting all suffixes of `s`
 *     corresponds to exactly ONE unique substring!
 *   - Algorithm:
 *     1. Initialize a Trie root and count = 0.
 *     2. For every starting index i from 0 to N-1:
 *          Traverse character ch = s[j] from j = i to N-1:
 *          If node.children[ch - 'a'] is null:
 *            Create new node and increment count.
 *          Move to child node.
 *     3. Return count (or count + 1 if including the empty substring).
 */

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
