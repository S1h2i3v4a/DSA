/**
 * Problem Name: Alien Dictionary
 * Platform: GeeksforGeeks / LeetCode 269
 * Difficulty: Hard
 * 
 * Time Complexity: O(N * L + K) where N is number of words, L is max word length, K is number of characters
 * Space Complexity: O(K) for adjacency list, in-degree array, and topological order queue
 * 
 * Approach:
 * Directed Graph + Topological Sort:
 * 1. Compare consecutive words in the dictionary: dict[i] and dict[i+1].
 * 2. Find the first differing character:
 *    char c1 = dict[i].charAt(j), char c2 = dict[i+1].charAt(j).
 *    This establishes a directed edge: c1 -> c2 (meaning c1 comes before c2 in alien alphabet).
 * 3. Handle prefix edge case: If word2 is a prefix of word1 (e.g. "abc", "ab"), ordering is invalid!
 * 4. Run Kahn's Algorithm (Topological Sort) on the directed graph:
 *    - Compute in-degrees of all K characters.
 *    - If topological sort sequence contains all K characters, valid order exists; return string.
 *    - If cycle exists (completed count < K), return "" (invalid dictionary order).
 */

import java.util.*;

class Alien_Dictionary {
    public String findOrder(String[] dict, int N, int K) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            adj.add(new ArrayList<>());
        }

        int[] inDegree = new int[K];

        // Step 1: Compare adjacent words to build directed graph
        for (int i = 0; i < N - 1; i++) {
            String w1 = dict[i];
            String w2 = dict[i + 1];

            // Edge case: prefix check
            if (w1.length() > w2.length() && w1.startsWith(w2)) {
                return "";
            }

            int minLen = Math.min(w1.length(), w2.length());
            for (int j = 0; j < minLen; j++) {
                if (w1.charAt(j) != w2.charAt(j)) {
                    int u = w1.charAt(j) - 'a';
                    int v = w2.charAt(j) - 'a';
                    adj.get(u).add(v);
                    inDegree[v]++;
                    break;
                }
            }
        }

        // Step 2: Kahn's Algorithm (BFS Topological Sort)
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < K; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        StringBuilder order = new StringBuilder();
        while (!queue.isEmpty()) {
            int u = queue.poll();
            order.append((char) (u + 'a'));

            for (int v : adj.get(u)) {
                inDegree[v]--;
                if (inDegree[v] == 0) {
                    queue.offer(v);
                }
            }
        }

        // Cycle check: if all K characters are in topological order
        if (order.length() < K) {
            return "";
        }

        return order.toString();
    }

    public static void main(String[] args) {
        Alien_Dictionary solver = new Alien_Dictionary();
        String[] dict = {"baa", "abcd", "abca", "cab", "cad"};
        int N = 5, K = 4; // Characters 'a', 'b', 'c', 'd'
        System.out.println("Alien Alphabet Order: " + solver.findOrder(dict, N, K));
        // Expected valid order: "bdac"
    }
}
