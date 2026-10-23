/*
QUESTION:
There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

Return true if you can finish all courses. Otherwise, return false.

APPROACH:
- We can model the problem as a directed graph, where each course is a node, and a prerequisite pair [ai, bi] indicates a directed edge from course bi to course ai.
- To check if we can finish all courses, we need to ensure that the directed graph is a DAG (Directed Acyclic Graph) with no cycles.
- If there are cycles in the graph, it means there is a circular dependency between courses, and we cannot finish all courses in such cases.
- To check for cycles, we can use Kahn's algorithm for Topological Sorting.
- If we can perform a successful Topological Sort (i.e., there is no cycle), then it means we can finish all courses.

COMPLEXITY ANALYSIS:
- Time Complexity: O(N + E), where N is the number of courses (nodes) and E is the number of prerequisites (edges) in the graph. We perform a BFS-like traversal of all nodes and edges.
- Space Complexity: O(N + E), where N is the number of courses (nodes) and E is the number of prerequisites (edges) in the graph. We use additional space to store the adjacency list and indegree of each node.
*/

import java.util.*;
import java.io.*;

// CODE:-
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

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
