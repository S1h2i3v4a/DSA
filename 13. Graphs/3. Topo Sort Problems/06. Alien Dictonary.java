/*
QUESTION:
Given a sorted dictionary of an alien language having N words and k starting alphabets of the standard dictionary. Find the order of characters in the alien language.

APPROACH:
- We are given a sorted dictionary of an alien language.
- To find the order of characters in the alien language, we can use a directed graph approach along with topological sorting.
- We create a directed graph where each node represents a character, and there is a directed edge from node 'a' to node 'b' if character 'a' comes before character 'b' in the alien language.
- We also keep track of the indegree of each node, which represents the number of characters that come before it in the alien language.
- We iterate through the dictionary and set the directed edges and indegrees accordingly.
- We then start with the nodes having an indegree of 0 (i.e., the characters that come first in the alien language) and perform a BFS-like traversal.
- During the traversal, we keep reducing the indegree of the nodes that come after the current node.
- The characters that become terminal nodes (i.e., their indegree becomes 0) during the traversal are the characters that come last in the alien language.
- We construct the order of characters based on the BFS traversal, and that will be the correct order of characters in the alien language.

COMPLEXITY ANALYSIS:
- Time Complexity: O(N), where N is the number of words in the dictionary. We iterate through the dictionary once to set the directed edges and indegrees.
- Space Complexity: O(K), where K is the number of starting alphabets in the standard dictionary. We use additional space to store the directed graph and indegrees.
*/

import java.util.*;
import java.io.*;

// CODE:-
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

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
