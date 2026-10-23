/**
 * Problem Name: Implement Trie (Prefix Tree)
 * Platform: LeetCode (LC 208) / GeeksforGeeks
 * Difficulty: Medium
 *
 * Time Complexity:
 *   - Insert: O(L) where L is length of the word
 *   - Search: O(L)
 *   - StartsWith: O(L)
 * Space Complexity: O(Total characters across all inserted words * 26)
 *
 * Approach:
 *   - A Trie (Prefix Tree) is an efficient retrieval data structure.
 *   - Each TrieNode holds:
 *     1. children: An array of 26 TrieNode pointers (for 'a' to 'z').
 *     2. isEnd: A boolean flag indicating whether this node represents the end of a valid word.
 *   - Operations:
 *     - insert(word): Walk/create nodes for each character; set isEnd = true on the final node.
 *     - search(word): Walk nodes; return true only if all characters exist and final isEnd is true.
 *     - startsWith(prefix): Walk nodes; return true if all prefix characters exist in the Trie.
 */

class TriePrefixTree {

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

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println("Search 'apple': " + trie.search("apple"));   // true
        System.out.println("Search 'app': " + trie.search("app"));       // false
        System.out.println("StartsWith 'app': " + trie.startsWith("app")); // true
        trie.insert("app");
        System.out.println("Search 'app' after insert: " + trie.search("app")); // true
    }
}
