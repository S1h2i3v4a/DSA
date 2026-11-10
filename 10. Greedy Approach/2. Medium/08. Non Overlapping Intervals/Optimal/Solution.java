/*
QUESTION:
Find minimum intervals to remove to make the rest non-overlapping in O(N log N) using Greedy Interval Scheduling.

APPROACH:
- Sort intervals by end time in ascending order.
- Greedily pick the first interval and record its end time `prevEnd`.
- For subsequent intervals:
  - If `interval[0] >= prevEnd`: compatible, keep it, update `prevEnd = interval[1]`.
  - Else: overlapping, increment `removals` count (greedy choice: discard interval ending later).

TIME COMPLEXITY: O(N log N) for sorting intervals by end time.
SPACE COMPLEXITY: O(1) constant auxiliary space.
*/

import java.util.Arrays;

class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) return 0;
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));

        int removals = 0;
        int prevEnd = intervals[0][1];

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= prevEnd) {
                prevEnd = intervals[i][1];
            } else {
                removals++;
            }
        }
        return removals;
    }
}
