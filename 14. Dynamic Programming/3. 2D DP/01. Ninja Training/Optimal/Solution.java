/*
QUESTION:
Ninja plans 3 activities per day over n days. Cannot do the same activity on consecutive days.
Find the maximum merit points Ninja can earn.

APPROACH:
- Space-Optimized Tabulation:
- Maintain a 1D array `prev` of size 4 representing previous day max points for each restricted last task.
- Compute `temp` array of size 4 for the current day and swap.

TIME COMPLEXITY: O(N * 4 * 3) = O(N).
SPACE COMPLEXITY: O(1) array of size 4.
*/

class Solution {
    public int maximumPoints(int[][] points, int n) {
        int[] prev = new int[4];

        prev[0] = Math.max(points[0][1], points[0][2]);
        prev[1] = Math.max(points[0][0], points[0][2]);
        prev[2] = Math.max(points[0][0], points[0][1]);
        prev[3] = Math.max(points[0][0], Math.max(points[0][1], points[0][2]));

        for (int day = 1; day < n; day++) {
            int[] temp = new int[4];
            for (int last = 0; last < 4; last++) {
                temp[last] = 0;
                for (int task = 0; task < 3; task++) {
                    if (task != last) {
                        temp[last] = Math.max(temp[last], points[day][task] + prev[task]);
                    }
                }
            }
            prev = temp;
        }

        return prev[3];
    }
}
