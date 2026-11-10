/*
QUESTION:
Given an array of intervals `intervals` where `intervals[i] = [starti, endi]`,
return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.

APPROACH:
- Sort intervals by start time.
- Recursive backtracking: at each interval, try including it (if non-overlapping with previous selected) or excluding it.
- Return `intervals.length - maxCompatibleIntervals`.

TIME COMPLEXITY: O(2^N) exponential combinations.
SPACE COMPLEXITY: O(N) recursion stack.
*/

import java.util.Arrays;

class Solution {
    private int solve(int[][] intervals, int idx, int prevEnd) {
        if (idx == intervals.length) return 0;

        int notPick = solve(intervals, idx + 1, prevEnd);
        int pick = 0;
        if (intervals[idx][0] >= prevEnd) {
            pick = 1 + solve(intervals, idx + 1, intervals[idx][1]);
        }
        return Math.max(pick, notPick);
    }

    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int maxKeep = solve(intervals, 0, Integer.MIN_VALUE);
        return intervals.length - maxKeep;
    }
}
