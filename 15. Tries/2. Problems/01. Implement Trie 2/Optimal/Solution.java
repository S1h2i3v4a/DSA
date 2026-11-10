/*
QUESTION:
Implement Trie II supporting: insert(word), countWordsEqualTo(word), countWordsStartingWith(prefix), and erase(word).

APPROACH:
- Trie Node maintains two integer counters:
  - `endsCount`: number of words ending at this node.
  - `prefixCount`: number of words passing through this node.
- All operations run in O(L) time.

TIME COMPLEXITY:
- insert: O(L).
- countWordsEqualTo: O(L).
- countWordsStartingWith: O(L).
- erase: O(L).
SPACE COMPLEXITY: O(N * L * 26) nodes.
*/

class Solution {
    static class Node {
        Node[] children = new Node[26];
        int endsCount = 0;
        int prefixCount = 0;
    }

    static class Trie {
        private final Node root;

        public Trie() {
            root = new Node();
        }

        public void insert(String word) {
            Node node = root;
            for (char ch : word.toCharArray()) {
                int idx = ch - 'a';
                if (node.children[idx] == null) {
                    node.children[idx] = new Node();
                }
                node = node.children[idx];
                node.prefixCount++;
            }
            node.endsCount++;
        }

        public int countWordsEqualTo(String word) {
            Node node = root;
            for (char ch : word.toCharArray()) {
                int idx = ch - 'a';
                if (node.children[idx] == null) return 0;
                node = node.children[idx];
            }
            return node.endsCount;
        }

        public int countWordsStartingWith(String prefix) {
            Node node = root;
            for (char ch : prefix.toCharArray()) {
                int idx = ch - 'a';
                if (node.children[idx] == null) return 0;
                node = node.children[idx];
            }
            return node.prefixCount;
        }

        public void erase(String word) {
            Node node = root;
            for (char ch : word.toCharArray()) {
                int idx = ch - 'a';
                if (node.children[idx] == null) return;
                node = node.children[idx];
                node.prefixCount--;
            }
            node.endsCount--;
        }
    }
}
