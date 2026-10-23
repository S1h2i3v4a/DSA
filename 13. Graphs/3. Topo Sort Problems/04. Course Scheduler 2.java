/*
QUESTION:
There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

Return the ordering of courses you should take to finish all courses. If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.

APPROACH:
- We can model the problem as a directed graph, where each course is a node, and a prerequisite pair [ai, bi] indicates a directed edge from course bi to course ai.
- To find the course ordering, we can use Kahn's algorithm for Topological Sorting.
- If we can perform a successful Topological Sort, it means we can finish all courses, and the order in which we pop the nodes from the queue will give us the correct course order.
- If there is a cycle in the graph (i.e., it is not possible to finish all courses), the algorithm will not be able to remove all nodes with indegree 0, and we will not get a valid course ordering.

COMPLEXITY ANALYSIS:
- Time Complexity: O(N + E), where N is the number of courses (nodes) and E is the number of prerequisites (edges) in the graph. We perform a BFS-like traversal of all nodes and edges.
- Space Complexity: O(N + E), where N is the number of courses (nodes) and E is the number of prerequisites (edges) in the graph. We use additional space to store the adjacency list and indegree of each node.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) adj.add(new ArrayList<>());
        int[] indegree = new int[numCourses];
        for (int[] pre : prerequisites) {
            adj.get(pre[1]).add(pre[0]);
            indegree[pre[0]]++;
        }
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) q.add(i);
        }
        int[] topo = new int[numCourses];
        int idx = 0;
        while (!q.isEmpty()) {
            int node = q.poll();
            topo[idx++] = node;
            for (int it : adj.get(node)) {
                indegree[it]--;
                if (indegree[it] == 0) q.add(it);
            }
        }
        return idx == numCourses ? topo : new int[0];
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
