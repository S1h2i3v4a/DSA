/**
 * Problem Name: Word Ladder
 * Platform: LeetCode (LC 127)
 * Difficulty: Hard
 * 
 * Time Complexity: O(N * L * 26) where N is number of words and L is word length
 * Space Complexity: O(N * L) for word set and BFS queue
 * 
 * Approach:
 * BFS Shortest Path:
 * 1. Store all dictionary words in a HashSet for O(1) lookups.
 * 2. If endWord is not in the set, return 0.
 * 3. Use a Queue storing Pair(word, step) initialized with (beginWord, 1).
 * 4. For each word, try substituting each of the L characters with all 'a'-'z' (26 options).
 * 5. If the new word exists in the set:
 *    - If newWord equals endWord, return step + 1.
 *    - Remove newWord from set (to mark visited) and push (newWord, step + 1) to queue.
 * 6. Return 0 if queue empties without reaching endWord.
 */

import java.util.*;

class Word_Ladder {
    private static class WordNode {
        String word;
        int step;

        WordNode(String word, int step) {
            this.word = word;
            this.step = step;
        }
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) return 0;

        Queue<WordNode> queue = new LinkedList<>();
        queue.offer(new WordNode(beginWord, 1));
        wordSet.remove(beginWord);

        while (!queue.isEmpty()) {
            WordNode curr = queue.poll();
            String word = curr.word;
            int step = curr.step;

            if (word.equals(endWord)) {
                return step;
            }

            char[] chars = word.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                char original = chars[i];

                for (char c = 'a'; c <= 'z'; c++) {
                    chars[i] = c;
                    String nextWord = new String(chars);

                    if (wordSet.contains(nextWord)) {
                        wordSet.remove(nextWord); // Mark visited
                        queue.offer(new WordNode(nextWord, step + 1));
                    }
                }

                chars[i] = original; // Restore
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        Word_Ladder solver = new Word_Ladder();
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");

        System.out.println("Shortest transformation length: " + solver.ladderLength(beginWord, endWord, wordList));
        // Expected: 5 ("hit" -> "hot" -> "dot" -> "dog" -> "cog")
    }
}
