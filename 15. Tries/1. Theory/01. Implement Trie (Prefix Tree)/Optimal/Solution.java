/*
QUESTION:
Implement the Trie class supporting insert, search, and startsWith.

APPROACH:
- 26-ary prefix tree (TrieNode array of size 26).
- Each node stores boolean flag `isEnd`.
- Traverse characters one-by-one in O(L) time.

TIME COMPLEXITY:
- insert: O(L) where L is length of word.
- search: O(L).
- startsWith: O(L).
SPACE COMPLEXITY: O(N * L * 26) in worst case for trie nodes.
*/

class Solution {
    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEnd = false;
    }

    static class Trie {
        private final TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode node = root;
            for (char ch : word.toCharArray()) {
                int idx = ch - 'a';
                if (node.children[idx] == null) {
                    node.children[idx] = new TrieNode();
                }
                node = node.children[idx];
            }
            node.isEnd = true;
        }

        public boolean search(String word) {
            TrieNode node = root;
            for (char ch : word.toCharArray()) {
                int idx = ch - 'a';
                if (node.children[idx] == null) {
                    return false;
                }
                node = node.children[idx];
            }
            return node.isEnd;
        }

        public boolean startsWith(String prefix) {
            TrieNode node = root;
            for (char ch : prefix.toCharArray()) {
                int idx = ch - 'a';
                if (node.children[idx] == null) {
                    return false;
                }
                node = node.children[idx];
            }
            return true;
        }
    }
}
