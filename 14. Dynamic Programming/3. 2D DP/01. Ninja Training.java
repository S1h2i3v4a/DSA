/*
QUESTION:-
Geek is going for n days training program, he can perform any one of these three activities Running, Fighting, and Learning Practice. Each activity has some point on each day. As Geek wants to improve all his skills, he can't do the same activity on two consecutive days. Help Geek to maximize his merit points as we were given a 2D array of n*3 points corresponding to each day and activity.

Example:
Input:
n = 3
points = [[1,2,5],[3,1,1],[3,3,3]]
Output:
11
Explanation:
Geek will learn a new move and earn 5 points on the first day,
then on the second day, he will do running and earn 3 points,
and on the third day, he will do fighting and earn 3 points.
So, the maximum points is 11.

Approach:
1. To maximize the merit points, we need to find the maximum sum of points such that the Geek can't perform the same activity on two consecutive days.
2. We can solve this problem using dynamic programming with memoization (top-down approach).
3. We define a helper function fmemo(n, prev, points, memo) that calculates the maximum points the Geek can earn from the nth day onwards, given that on the (n-1)th day, he performed the activity indexed by 'prev'.
4. The function checks if the maximum points for the (n-1)th day and 'prev' activity is already calculated and stored in the memo array. If yes, it returns the value from memo.
5. Otherwise, it calculates the maximum points for the nth day by considering all three activities except the one performed on the (n-1)th day. It then adds the points for the nth day and recursively calls the function for the (n-1)th day with the new activity and updates the maximum points.
6. The base case is when n is less than 0, in which case the function returns 0 as there are no days to earn points.
7. In the main function maximumPoints(points, n), we create a dp (memo) array of size n initialized with -1 and call the fmemo function for the nth day with the initial activity as -1 (since no activity is performed on the first day).
8. Finally, we return the maximum points obtained for all three possible starting activities (running, fighting, learning practice) as the result.

Complexity Analysis:
- The time complexity of the fmemo function is O(n) because we are calculating the maximum points for each day only once and storing it in the memo array.
- The space complexity is also O(n) to store the memo array.

*/

import java.io.*;
import java.util.*;
import java.util.Arrays;

// CODE:-
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

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
