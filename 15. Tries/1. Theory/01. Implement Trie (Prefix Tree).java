/*
QUESTION:
Implement the Trie class, which supports insertion, search, and startsWith operations.

Approach:
- Create a TrieNode class with an array of child nodes, each representing a letter.
- The Trie class maintains a root TrieNode.
- For insertion, traverse the trie, creating new nodes as necessary for each character.
- For search, traverse the trie, checking if nodes exist for each character and if the final node is marked as a complete word.
- For startsWith, traverse the trie similarly to search, but don't require the final node to be marked as a complete word.

Complexity Analysis:
- Insertion, search, and startsWith operations take O(L) time, where L is the length of the word or prefix.
- Space complexity is O(N * M), where N is the number of words and M is the average length of the words.

CODE:-
*/

import java.io.*;
import java.util.*;

// CODE:-
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

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
