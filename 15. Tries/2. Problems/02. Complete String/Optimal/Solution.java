/*
QUESTION:
Find longest complete string (every prefix exists in array A).

APPROACH:
- Insert all strings into a Trie.
- For each string, traverse its Trie path; if all traversed nodes have `isEnd == true`, it is complete.
- Track longest lexicographically smallest complete string.

TIME COMPLEXITY: O(N * L) single pass insertion and query.
SPACE COMPLEXITY: O(N * L * 26) Trie nodes.
*/

class Solution {
    static class Node {
        Node[] children = new Node[26];
        boolean isEnd = false;
    }

    static class Trie {
        private final Node root = new Node();

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
                if (!node.isEnd) return false;
            }
            return true;
        }
    }

    public static String completeString(int n, String[] a) {
        Trie trie = new Trie();
        for (String s : a) trie.insert(s);

        String longest = "";
        for (String s : a) {
            if (trie.checkIfAllPrefixesExist(s)) {
                if (s.length() > longest.length()) {
                    longest = s;
                } else if (s.length() == longest.length() && s.compareTo(longest) < 0) {
                    longest = s;
                }
            }
        }

        return longest.isEmpty() ? "None" : longest;
    }
}
