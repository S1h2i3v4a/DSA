/**
 * Problem Name: Geek's Training (Ninja's Training)
 * Platform: GeeksforGeeks / Coding Ninjas
 * Difficulty: Medium
 *
 * Time Complexity:
 *   - Memoization: O(N * 4 * 3) = O(N)
 *   - Tabulation: O(N * 4 * 3) = O(N)
 *   - Space-Optimized DP: O(N * 4 * 3) = O(N)
 * Space Complexity:
 *   - Memoization: O(N * 4) table + O(N) recursion stack
 *   - Tabulation: O(N * 4) table
 *   - Space-Optimized DP: O(1) auxiliary space (using rolling 1D arrays of size 4)
 *
 * Approach:
 *   - Geek plans an N-day schedule choosing one of 3 activities daily: Running (0), Fighting (1), Learning (2).
 *   - Constraint: Cannot perform the same activity on two consecutive days.
 *   - Goal: Maximize total merit points earned over N days.
 *   - State Definition:
 *     dp[day][lastActivity] = maximum merit points up to `day` given that on `day + 1`,
 *     the activity performed was `lastActivity` (0, 1, 2, or 3 for "no prior restriction").
 *   - Recurrence:
 *     dp[day][last] = max(points[day][task] + dp[day - 1][task]) for all task != last.
 *   - We provide:
 *     1. Top-Down DP with Memoization
 *     2. Bottom-Up DP with Tabulation
 *     3. Space-Optimized DP using O(1) extra space.
 */

import java.util.Arrays;

class GeeksTraining {

    // ==========================================
    // Approach 1: Top-Down DP (Memoization)
    // ==========================================
    public static int maximumPointsMemo(int[][] points, int n) {
        int[][] dp = new int[n][4];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return memoHelper(n - 1, 3, points, dp);
    }

    private static int memoHelper(int day, int last, int[][] points, int[][] dp) {
        if (day == 0) {
            int maxPoints = 0;
            for (int task = 0; task < 3; task++) {
                if (task != last) {
                    maxPoints = Math.max(maxPoints, points[0][task]);
                }
            }
            return maxPoints;
        }

        if (dp[day][last] != -1) {
            return dp[day][last];
        }

        int maxPoints = 0;
        for (int task = 0; task < 3; task++) {
            if (task != last) {
                int score = points[day][task] + memoHelper(day - 1, task, points, dp);
                maxPoints = Math.max(maxPoints, score);
            }
        }

        return dp[day][last] = maxPoints;
    }

    // ==========================================
    // Approach 2: Bottom-Up DP (Tabulation)
    // ==========================================
    public static int maximumPointsTabulation(int[][] points, int n) {
        int[][] dp = new int[n][4];

        // Base case for Day 0
        dp[0][0] = Math.max(points[0][1], points[0][2]);
        dp[0][1] = Math.max(points[0][0], points[0][2]);
        dp[0][2] = Math.max(points[0][0], points[0][1]);
        dp[0][3] = Math.max(points[0][0], Math.max(points[0][1], points[0][2]));

        // Fill table for day 1 to n - 1
        for (int day = 1; day < n; day++) {
            for (int last = 0; last < 4; last++) {
                dp[day][last] = 0;
                for (int task = 0; task < 3; task++) {
                    if (task != last) {
                        int score = points[day][task] + dp[day - 1][task];
                        dp[day][last] = Math.max(dp[day][last], score);
                    }
                }
            }
        }

        return dp[n - 1][3];
    }

    // ==========================================
    // Approach 3: Space-Optimized DP - O(1) Space
    // ==========================================
    public static int maximumPointsSpaceOptimized(int[][] points, int n) {
        int[] prev = new int[4];

        // Base case for Day 0
        prev[0] = Math.max(points[0][1], points[0][2]);
        prev[1] = Math.max(points[0][0], points[0][2]);
        prev[2] = Math.max(points[0][0], points[0][1]);
        prev[3] = Math.max(points[0][0], Math.max(points[0][1], points[0][2]));

        for (int day = 1; day < n; day++) {
            int[] curr = new int[4];
            for (int last = 0; last < 4; last++) {
                curr[last] = 0;
                for (int task = 0; task < 3; task++) {
                    if (task != last) {
                        curr[last] = Math.max(curr[last], points[day][task] + prev[task]);
                    }
                }
            }
            prev = curr;
        }

        return prev[3];
    }

    public static void main(String[] args) {
        // Test Case 1:
        // n = 3, points = [[1, 2, 5], [3, 1, 1], [3, 3, 3]]
        // Expected: 11 (Day 0: 5, Day 1: 3, Day 2: 3)
        int[][] points1 = {
            {1, 2, 5},
            {3, 1, 1},
            {3, 3, 3}
        };
        int n1 = points1.length;
        System.out.println("Test 1 (Memoization): " + maximumPointsMemo(points1, n1)); // 11
        System.out.println("Test 1 (Tabulation): " + maximumPointsTabulation(points1, n1)); // 11
        System.out.println("Test 1 (Space-Optimized): " + maximumPointsSpaceOptimized(points1, n1)); // 11

        // Test Case 2:
        // n = 3, points = [[10, 40, 70], [20, 50, 80], [30, 60, 90]]
        // Expected: 210 (Day 0: 70, Day 1: 50, Day 2: 90)
        int[][] points2 = {
            {10, 40, 70},
            {20, 50, 80},
            {30, 60, 90}
        };
        int n2 = points2.length;
        System.out.println("Test 2 (Memoization): " + maximumPointsMemo(points2, n2)); // 210
        System.out.println("Test 2 (Tabulation): " + maximumPointsTabulation(points2, n2)); // 210
        System.out.println("Test 2 (Space-Optimized): " + maximumPointsSpaceOptimized(points2, n2)); // 210
    }
}
