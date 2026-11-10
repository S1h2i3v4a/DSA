/*
QUESTION:
Count total distinct substrings of string s.

APPROACH:
- Insert all suffixes of s into a Trie.
- Every node in the Trie corresponds to a unique distinct substring.
- Total distinct substrings = count of distinct nodes created (+ 1 for empty string).

TIME COMPLEXITY: O(N^2) no substring hashing or string allocations.
SPACE COMPLEXITY: O(N^2 * 26) Trie nodes.
*/

class Solution {
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
                    count++;
                }
                node = node.children[idx];
            }
        }

        return count + 1; // including empty substring
    }
}
