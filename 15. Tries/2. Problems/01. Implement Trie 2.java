/*
QUESTION:
Ninja has to implement a data structure "TRIE" from scratch. Implement the functions as follows:
1) Trie(): Initializes the trie object.
2) insert("WORD"): Inserts the string "WORD" into the trie.
3) countWordsEqualTo("WORD"): Returns how many times the string "WORD" is present in the trie.
4) countWordsStartingWith("PREFIX"): Returns how many words in the trie have the string "PREFIX" as a prefix.
5) erase("WORD"): Deletes one occurrence of the string "WORD" from the trie.

Approach:
- Create a TrieNode class with an array of child nodes, each representing a letter.
- Maintain the count of words with a specific prefix (cntPre) and the count of words (cntWord) at each node.
- Implement insert, countWordsEqualTo, countWordsStartingWith, and erase functions using TrieNode operations.

Complexity Analysis:
- All operations take O(L) time, where L is the length of the word or prefix.
- Space complexity is O(N * M), where N is the number of words and M is the average length of the words.
*/

import java.io.*;
import java.util.*;

// CODE:-
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

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
