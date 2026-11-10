/*
QUESTION:
Given two words, `beginWord` and `endWord`, and a dictionary `wordList`,
return the number of words in the shortest transformation sequence from `beginWord` to `endWord`, or 0 if no such sequence exists.

APPROACH:
- Put all dictionary words into a HashSet.
- Use BFS queue storing `(word, level)`.
- For current word, replace each character with 'a' through 'z'.
- If transformed word is in the set:
  - If equal to `endWord`, return `level + 1`.
  - Remove from set to prevent cycles and enqueue with `level + 1`.

TIME COMPLEXITY: O(N * L * 26) where N is number of words and L is word length.
SPACE COMPLEXITY: O(N * L) for queue and word set.
*/

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

class Solution {
    private static class Pair {
        String word;
        int step;
        Pair(String word, int step) {
            this.word = word;
            this.step = step;
        }
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>(wordList);
        if (!set.contains(endWord)) return 0;

        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(beginWord, 1));
        set.remove(beginWord);

        while (!queue.isEmpty()) {
            Pair curr = queue.poll();
            String word = curr.word;
            int step = curr.step;

            if (word.equals(endWord)) return step;

            char[] chars = word.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                char orig = chars[i];
                for (char c = 'a'; c <= 'z'; c++) {
                    chars[i] = c;
                    String nextWord = new String(chars);
                    if (set.contains(nextWord)) {
                        set.remove(nextWord);
                        queue.offer(new Pair(nextWord, step + 1));
                    }
                }
                chars[i] = orig;
            }
        }
        return 0;
    }
}
