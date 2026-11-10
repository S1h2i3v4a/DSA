/*
QUESTION:-
Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.

Example:
Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
*/

/*
APPROACH:-
Sort intervals. For each interval, scan through all subsequent intervals and merge if overlapping, maintaining visited status.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> ans = new ArrayList<>();
        int n = intervals.length;
        for (int i = 0; i < n; i++) {
            int start = intervals[i][0];
            int end = intervals[i][1];
            if (!ans.isEmpty() && end <= ans.get(ans.size() - 1)[1]) continue;
            for (int j = i + 1; j < n; j++) {
                if (intervals[j][0] <= end) {
                    end = Math.max(end, intervals[j][1]);
                } else break;
            }
            ans.add(new int[] { start, end });
        }
        return ans.toArray(new int[ans.size()][]);
    }
}

// TIME COMPLEXITY = O(N log N + 2N)
// SPACE COMPLEXITY = O(1) auxiliary
