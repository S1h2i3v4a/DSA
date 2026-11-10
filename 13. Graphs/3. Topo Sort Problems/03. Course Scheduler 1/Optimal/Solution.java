/*
QUESTION:
There are a total of `numCourses` courses you have to take, labeled from 0 to `numCourses - 1`.
You are given an array `prerequisites` where `prerequisites[i] = [ai, bi]` indicates that you must take course `bi` first if you want to take course `ai`.
Return true if you can finish all courses. Otherwise, return false.

APPROACH:
- Directed graph where each edge `bi -> ai` represents a prerequisite.
- The courses can be finished if and only if the directed graph contains NO cycles.
- Run Kahn's Algorithm (BFS Topo Sort): count how many courses can be processed with in-degree 0.
- Return `count == numCourses`.

TIME COMPLEXITY: O(V + E) where V = numCourses and E = prerequisites.length.
SPACE COMPLEXITY: O(V + E) for adjacency list.
*/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) adj.add(new ArrayList<>());
        int[] inDegree = new int[numCourses];

        for (int[] pre : prerequisites) {
            adj.get(pre[1]).add(pre[0]);
            inDegree[pre[0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) queue.offer(i);
        }

        int processed = 0;
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            processed++;

            for (int next : adj.get(curr)) {
                inDegree[next]--;
                if (inDegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }
        return processed == numCourses;
    }
}
