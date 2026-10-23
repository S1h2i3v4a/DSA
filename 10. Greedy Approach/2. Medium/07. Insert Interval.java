/*
Question:
You are given an array of non-overlapping intervals intervals where intervals[i] = [starti, endi] represent the start and the end of the ith interval and intervals is sorted in ascending order by starti. You are also given an interval newInterval = [start, end] that represents the start and end of another interval.

Insert newInterval into intervals such that intervals is still sorted in ascending order by starti and intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).

Return intervals after the insertion.

Example:
Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]
Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].

Approach:
- We iterate through the intervals and compare them with the newInterval.
- There are three possible cases:
  1. The newInterval is before the current interval: We add the newInterval and update the newInterval to the current interval.
  2. The newInterval is after the current interval: We add the current interval to the result.
  3. The newInterval overlaps with the current interval: We update the newInterval to cover the merged interval.

Complexity Analysis:
- The time complexity of this approach is O(N), where N is the number of intervals.
- We iterate through the intervals once.
- The space complexity is O(1) as we are using a constant amount of additional space.

Code:
*/

import java.util.*;
import java.io.*;

// CODE:-
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

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
