/*
QUESTION:
Ninja plans 3 activities per day over n days. Cannot do the same activity on consecutive days.
Find the maximum merit points Ninja can earn.

APPROACH:
- Pure recursion: for day `d` and previous task `last`, try all 3 tasks where task != last.
- Take maximum points possible.

TIME COMPLEXITY: O(3^N) exponential branching.
SPACE COMPLEXITY: O(N) recursion stack.
*/

class Solution {
    public int maximumPoints(int[][] points, int n) {
        return solve(n - 1, 3, points);
    }

    private int solve(int day, int last, int[][] points) {
        if (day == 0) {
            int maxi = 0;
            for (int task = 0; task < 3; task++) {
                if (task != last) {
                    maxi = Math.max(maxi, points[0][task]);
                }
            }
            return maxi;
        }

        int maxi = 0;
        for (int task = 0; task < 3; task++) {
            if (task != last) {
                int point = points[day][task] + solve(day - 1, task, points);
                maxi = Math.max(maxi, point);
            }
        }
        return maxi;
    }
}
