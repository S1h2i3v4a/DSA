/**
 * Problem Name: Insert Interval
 * Platform: LeetCode (LC 57)
 * Difficulty: Medium
 * 
 * Time Complexity: O(N) single pass through intervals
 * Space Complexity: O(N) to construct output result list
 * 
 * Approach:
 * Three-Phase Single Pass:
 * 1. Add all intervals ending before newInterval starts (interval[1] < newInterval[0]).
 * 2. Merge overlapping intervals (interval[0] <= newInterval[1]):
 *    newInterval[0] = Math.min(newInterval[0], interval[0])
 *    newInterval[1] = Math.max(newInterval[1], interval[1])
 *    Add merged newInterval to result.
 * 3. Add all remaining intervals starting after newInterval ends.
 */

import java.util.*;

class Insert_Interval {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int i = 0;
        int n = intervals.length;

        // Phase 1: Add intervals ending before newInterval starts
        while (i < n && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }

        // Phase 2: Merge overlapping intervals with newInterval
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        result.add(newInterval);

        // Phase 3: Add remaining intervals
        while (i < n) {
            result.add(intervals[i]);
            i++;
        }

        return result.toArray(new int[result.size()][]);
    }

    public static void main(String[] args) {
        Insert_Interval solver = new Insert_Interval();
        int[][] intervals = {{1, 3}, {6, 9}};
        int[] newInterval = {2, 5};
        int[][] res = solver.insert(intervals, newInterval);
        System.out.print("Merged intervals: ");
        for (int[] interval : res) {
            System.out.print(Arrays.toString(interval) + " ");
        }
        System.out.println(); // Expected: [[1, 5], [6, 9]]
    }
}
