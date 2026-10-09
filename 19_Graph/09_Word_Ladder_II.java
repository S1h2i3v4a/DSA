/**
 * Problem Name: Word Ladder II
 * Platform: LeetCode (LC 126)
 * Difficulty: Hard
 * 
 * Time Complexity: O(N * L * 26 + Paths)
 * Space Complexity: O(N * L) for distance map and recursion stack
 * 
 * Approach:
 * Optimal Two-Phase Strategy (BFS Distances + DFS Backtracking):
 * 1. Phase 1 (BFS):
 *    Start BFS from beginWord to compute the shortest level/distance to all reachable words:
 *    Map<String, Integer> distMap.
 * 2. Phase 2 (DFS Backtracking):
 *    Backtrack starting from endWord backwards to beginWord using distMap:
 *    At word 'curr', check 1-letter neighbors 'prev'.
 *    If distMap contains 'prev' and distMap.get(prev) == distMap.get(curr) - 1:
 *      Recurse backwards.
 *    When beginWord is reached, reverse the collected path and add to results.
 */

import java.util.*;

class Word_Ladder_II {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        List<List<String>> result = new ArrayList<>();
        if (!wordSet.contains(endWord)) return result;

        Map<String, Integer> distMap = new HashMap<>();
        Queue<String> queue = new LinkedList<>();

        // Phase 1: BFS to find shortest distance from beginWord to all nodes
        queue.offer(beginWord);
        distMap.put(beginWord, 0);
        wordSet.remove(beginWord);

        while (!queue.isEmpty()) {
            String word = queue.poll();
            int currDist = distMap.get(word);

            if (word.equals(endWord)) break;

            char[] chars = word.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                char orig = chars[i];
                for (char c = 'a'; c <= 'z'; c++) {
                    chars[i] = c;
                    String nextWord = new String(chars);

                    if (wordSet.contains(nextWord)) {
                        distMap.put(nextWord, currDist + 1);
                        wordSet.remove(nextWord);
                        queue.offer(nextWord);
                    }
                }
                chars[i] = orig;
            }
        }

        // If endWord is unreachable
        if (!distMap.containsKey(endWord)) return result;

        // Phase 2: DFS Backtracking from endWord back to beginWord
        List<String> path = new ArrayList<>();
        path.add(endWord);
        dfs(endWord, beginWord, distMap, path, result);

        return result;
    }

    private void dfs(String curr, String beginWord, Map<String, Integer> distMap,
                     List<String> path, List<List<String>> result) {
        if (curr.equals(beginWord)) {
            List<String> validPath = new ArrayList<>(path);
            Collections.reverse(validPath);
            result.add(validPath);
            return;
        }

        int currDist = distMap.get(curr);
        char[] chars = curr.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            char orig = chars[i];
            for (char c = 'a'; c <= 'z'; c++) {
                chars[i] = c;
                String prevWord = new String(chars);

                if (distMap.containsKey(prevWord) && distMap.get(prevWord) == currDist - 1) {
                    path.add(prevWord);
                    dfs(prevWord, beginWord, distMap, path, result);
                    path.remove(path.size() - 1); // Backtrack
                }
            }
            chars[i] = orig;
        }
    }

    public static void main(String[] args) {
        Word_Ladder_II solver = new Word_Ladder_II();
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");

        List<List<String>> paths = solver.findLadders(beginWord, endWord, wordList);
        System.out.println("Shortest transformation sequences:");
        for (List<String> seq : paths) {
            System.out.println(seq);
        }
        // Expected:
        // [hit, hot, dot, dog, cog]
        // [hit, hot, lot, log, cog]
    }
}
