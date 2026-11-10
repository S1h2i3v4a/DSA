/*
QUESTION:
Implement Trie II supporting: insert(word), countWordsEqualTo(word), countWordsStartingWith(prefix), and erase(word).

APPROACH:
- Maintain frequency map `Map<String, Integer>` for exact words.
- For `countWordsStartingWith(prefix)`, iterate all keys in map and sum counts of keys starting with prefix.

TIME COMPLEXITY:
- insert: O(L).
- countWordsEqualTo: O(L).
- countWordsStartingWith: O(N * L) where N is number of unique words.
- erase: O(L).
SPACE COMPLEXITY: O(N * L).
*/

import java.util.HashMap;
import java.util.Map;

class Solution {
    static class Trie {
        private final Map<String, Integer> wordCount;

        public Trie() {
            wordCount = new HashMap<>();
        }

        public void insert(String word) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }

        public int countWordsEqualTo(String word) {
            return wordCount.getOrDefault(word, 0);
        }

        public int countWordsStartingWith(String prefix) {
            int count = 0;
            for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
                if (entry.getKey().startsWith(prefix)) {
                    count += entry.getValue();
                }
            }
            return count;
        }

        public void erase(String word) {
            if (wordCount.containsKey(word)) {
                int cnt = wordCount.get(word);
                if (cnt == 1) {
                    wordCount.remove(word);
                } else {
                    wordCount.put(word, cnt - 1);
                }
            }
        }
    }
}
