/**
 * Problem Name: Activity Selection (N Meetings in One Room)
 * Platform: GeeksforGeeks / LeetCode 435
 * Difficulty: Medium
 * 
 * Time Complexity: O(N log N) for sorting activities by finish time
 * Space Complexity: O(N) to store activity objects
 * 
 * Approach:
 * Greedy Strategy:
 * Always pick the next activity that finishes earliest (smallest finish time).
 * 1. Pair start and finish times.
 * 2. Sort activities by finish time in non-decreasing order.
 * 3. Select the first activity.
 * 4. Iterate through remaining activities and pick an activity if its start time > finish time of last selected activity.
 */

import java.util.*;

class Activity_Selection {
    static class Activity {
        int start;
        int finish;

        Activity(int start, int finish) {
            this.start = start;
            this.finish = finish;
        }
    }

    public int maxActivities(int[] start, int[] end, int n) {
        Activity[] arr = new Activity[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new Activity(start[i], end[i]);
        }

        // Sort activities by finish time ascending
        Arrays.sort(arr, (a, b) -> Integer.compare(a.finish, b.finish));

        int count = 1;
        int lastFinish = arr[0].finish;

        for (int i = 1; i < n; i++) {
            if (arr[i].start > lastFinish) {
                count++;
                lastFinish = arr[i].finish;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Activity_Selection solver = new Activity_Selection();
        int[] start = {1, 3, 0, 5, 8, 5};
        int[] end = {2, 4, 6, 7, 9, 9};
        int n = start.length;
        System.out.println("Maximum non-overlapping activities: " + solver.maxActivities(start, end, n)); // Expected: 4
    }
}
