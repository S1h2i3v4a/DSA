/*
QUESTION:
A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:
1. Every adjacent pair of words differs by a single letter.
2. Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
3. sk == endWord

Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.

APPROACH:
- We can model this problem as a graph where each word is a node and there is an edge between two words if they differ by a single letter.
- First, we create an adjacency list to represent the graph based on the given wordList. Two words are adjacent if they differ by one character.
- Then, we use Breadth-First Search (BFS) to find the shortest transformation sequence from beginWord to endWord.
- We start BFS from the beginWord and traverse the graph level by level, marking visited words to avoid revisiting them.
- During the BFS, if we reach the endWord, we return the length of the transformation sequence.

COMPLEXITY ANALYSIS:
- Time Complexity: O(n^2 * m), where n is the size of wordList and m is the average length of the words in wordList. In the worst case, we may need to compare every pair of words in wordList to create the adjacency list.
- Space Complexity: O(n^2), where n is the size of wordList. We use additional space for the adjacency list and the visited map.
*/

import java.util.*;
import java.io.*;

// CODE:-
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

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
