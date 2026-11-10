/*
QUESTION:
Implement the Trie class supporting insert, search, and startsWith.

APPROACH:
- Store inserted words in a dynamic List.
- `search(word)`: linearly check if word exists in list.
- `startsWith(prefix)`: linearly check if any word in list starts with prefix.

TIME COMPLEXITY:
- insert: O(1).
- search: O(N * L) where N is number of words and L is word length.
- startsWith: O(N * L).
SPACE COMPLEXITY: O(N * L) storing all words.
*/

import java.util.ArrayList;
import java.util.List;

class Solution {
    static class Trie {
        private final List<String> words;

        public Trie() {
            words = new ArrayList<>();
        }

        public void insert(String word) {
            words.add(word);
        }

        public boolean search(String word) {
            for (String w : words) {
                if (w.equals(word)) return true;
            }
            return false;
        }

        public boolean startsWith(String prefix) {
            for (String w : words) {
                if (w.startsWith(prefix)) return true;
            }
            return false;
        }
    }
}
