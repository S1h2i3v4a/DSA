/*
QUESTION:
Given a sorted dictionary of an alien language having N words and k starting alphabets of standard dictionary.
Find the order of characters in the alien language.

APPROACH:
- Compare adjacent words `dict[i]` and `dict[i + 1]`.
- Find the first mismatched character `c1` and `c2`: add directed edge `c1 -> c2`.
- Run Kahn's algorithm on the K characters.
- If topo sort contains all K characters, return string; else empty string (cycle detected).

TIME COMPLEXITY: O(N * L + K) where L is max word length.
SPACE COMPLEXITY: O(K + E).
*/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    public String findOrder(String[] dict, int N, int K) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < K; i++) adj.add(new ArrayList<>());
        int[] inDegree = new int[K];

        for (int i = 0; i < N - 1; i++) {
            String w1 = dict[i];
            String w2 = dict[i + 1];
            int len = Math.min(w1.length(), w2.length());

            for (int j = 0; j < len; j++) {
                if (w1.charAt(j) != w2.charAt(j)) {
                    int u = w1.charAt(j) - 'a';
                    int v = w2.charAt(j) - 'a';
                    adj.get(u).add(v);
                    inDegree[v]++;
                    break;
                }
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < K; i++) {
            if (inDegree[i] == 0) queue.offer(i);
        }

        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            sb.append((char) (curr + 'a'));

            for (int next : adj.get(curr)) {
                inDegree[next]--;
                if (inDegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        return sb.length() == K ? sb.toString() : "";
    }
}
