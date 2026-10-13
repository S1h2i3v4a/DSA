/**
 * Problem Name: Minimum Multiplications to Reach End
 * Platform: GeeksforGeeks
 * Difficulty: Medium
 * 
 * Time Complexity: O(100000 * N) where N is array length
 * Space Complexity: O(100000) for distance array and BFS queue
 * 
 * Approach:
 * BFS on Modulo-100000 State Graph:
 * Since all multiplications are modulo 100000, there are at most 100000 unique states (0 to 99999).
 * Because every multiplication step increments the step count by 1 (unit weight), standard BFS
 * guarantees the shortest path:
 * 1. Initialize dist[100000] array with -1, and dist[start] = 0.
 * 2. If start == end, return 0.
 * 3. Enqueue Pair(start, steps = 0).
 * 4. While Queue is not empty:
 *    - Poll (node, steps).
 *    - For each multiplier in arr[]:
 *      next = (node * multiplier) % 100000.
 *      If steps + 1 < dist[next] (or dist[next] == -1):
 *        dist[next] = steps + 1;
 *        If next == end, return steps + 1.
 *        Enqueue (next, steps + 1).
 * 5. Return -1 if end is unreachable.
 */

import java.util.*;

class Minimum_Multiplications_To_Reach_End {
    private static class State {
        int node;
        int steps;

        State(int node, int steps) {
            this.node = node;
            this.steps = steps;
        }
    }

    public int minimumMultiplications(int[] arr, int start, int end) {
        if (start == end) return 0;

        int MOD = 100000;
        int[] dist = new int[MOD];
        Arrays.fill(dist, -1);
        dist[start] = 0;

        Queue<State> queue = new LinkedList<>();
        queue.offer(new State(start, 0));

        while (!queue.isEmpty()) {
            State curr = queue.poll();
            int node = curr.node;
            int steps = curr.steps;

            for (int factor : arr) {
                int next = (int) (((long) node * factor) % MOD);

                if (dist[next] == -1) {
                    dist[next] = steps + 1;
                    if (next == end) {
                        return steps + 1;
                    }
                    queue.offer(new State(next, steps + 1));
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        Minimum_Multiplications_To_Reach_End solver = new Minimum_Multiplications_To_Reach_End();
        int[] arr1 = {2, 5, 7};
        int start1 = 3, end1 = 30;
        System.out.println("Min multiplications (start=3, end=30): " + solver.minimumMultiplications(arr1, start1, end1)); // Expected: 2 (3*2=6, 6*5=30)

        int[] arr2 = {3, 4, 65};
        int start2 = 7, end2 = 66175;
        System.out.println("Min multiplications (start=7, end=66175): " + solver.minimumMultiplications(arr2, start2, end2)); // Expected: 4
    }
}
