/**
 * Problem Name: Course Schedule (Detect Cycle in Directed Graph / Topological Sort)
 * Platform: LeetCode (LC 207)
 * Difficulty: Medium
 * 
 * Time Complexity: O(V + E) where V is numCourses and E is prerequisites length
 * Space Complexity: O(V + E) for adjacency list, in-degree array, and BFS queue
 * 
 * Approach:
 * Kahn's Algorithm (BFS Topological Sort):
 * 1. Build adjacency list: prerequisite [u, v] means edge v -> u.
 * 2. Calculate in-degree for all vertices.
 * 3. Add all vertices with in-degree == 0 into a Queue.
 * 4. While Queue is not empty:
 *    - Poll vertex, increment completedCourses count.
 *    - For each neighbor, decrement in-degree; if in-degree becomes 0, add to Queue.
 * 5. If completedCourses == numCourses, all courses can be finished (no directed cycle).
 */

import java.util.*;

class Course_Schedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }

        int[] inDegree = new int[numCourses];

        for (int[] pre : prerequisites) {
            int course = pre[0];
            int prerequisite = pre[1];
            adj.get(prerequisite).add(course);
            inDegree[course]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        int completedCourses = 0;
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            completedCourses++;

            for (int nextCourse : adj.get(curr)) {
                inDegree[nextCourse]--;
                if (inDegree[nextCourse] == 0) {
                    queue.offer(nextCourse);
                }
            }
        }

        return completedCourses == numCourses;
    }

    public static void main(String[] args) {
        Course_Schedule solver = new Course_Schedule();
        int[][] pre1 = {{1, 0}};
        System.out.println("Can finish 2 courses with [[1, 0]]: " + solver.canFinish(2, pre1)); // Expected: true

        int[][] pre2 = {{1, 0}, {0, 1}};
        System.out.println("Can finish 2 courses with [[1, 0], [0, 1]]: " + solver.canFinish(2, pre2)); // Expected: false
    }
}
