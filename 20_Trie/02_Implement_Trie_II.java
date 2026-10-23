/**
 * Problem Name: Implement Trie II (Prefix Tree with Word & Prefix Counts & Deletion)
 * Platform: Coding Ninjas / GeeksforGeeks
 * Difficulty: Medium
 *
 * Time Complexity: O(L) for insert, countWordsEqualTo, countWordsStartingWith, and erase
 * Space Complexity: O(Total characters * 26)
 *
 * Approach:
 *   - Extends basic Trie by maintaining two counters in each TrieNode:
 *     1. `endsCount`: Number of inserted words that terminate at this node.
 *     2. `prefixCount`: Number of inserted words that pass through this node as a prefix.
 *   - Operations:
 *     - insert(word): Traverse nodes, increment prefixCount at each step, increment endsCount at the end.
 *     - countWordsEqualTo(word): Traverse to the end node of word and return its endsCount.
 *     - countWordsStartingWith(prefix): Traverse to the end node of prefix and return its prefixCount.
 *     - erase(word): Traverse nodes, decrement prefixCount at each step, decrement endsCount at the end.
 */

class TrieII {

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
                if (node.children[idx] == null) {
                    return 0;
                }
                node = node.children[idx];
            }
            return node.endsCount;
        }

        public int countWordsStartingWith(String prefix) {
            Node node = root;
            for (char ch : prefix.toCharArray()) {
                int idx = ch - 'a';
                if (node.children[idx] == null) {
                    return 0;
                }
                node = node.children[idx];
            }
            return node.prefixCount;
        }

        public void erase(String word) {
            Node node = root;
            for (char ch : word.toCharArray()) {
                int idx = ch - 'a';
                node = node.children[idx];
                node.prefixCount--;
            }
            node.endsCount--;
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        trie.insert("apple");
        trie.insert("apps");
        trie.insert("app");

        System.out.println("Words equal to 'apple': " + trie.countWordsEqualTo("apple")); // 2
        System.out.println("Words starting with 'app': " + trie.countWordsStartingWith("app")); // 4
        trie.erase("apple");
        System.out.println("Words equal to 'apple' after erase: " + trie.countWordsEqualTo("apple")); // 1
        System.out.println("Words starting with 'app' after erase: " + trie.countWordsStartingWith("app")); // 3
    }
}
