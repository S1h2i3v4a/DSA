/*
QUESTION:-
Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.

Example:
Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
*/

/*
APPROACH:-
Sort intervals by start time. Linearly scan: if current interval overlaps with the last added interval (current.start <= last.end), merge them by updating last.end = max(last.end, current.end). Otherwise, add current as a new interval.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> ans = new ArrayList<>();
        for (int[] interval : intervals) {
            if (ans.isEmpty() || interval[0] > ans.get(ans.size() - 1)[1]) {
                ans.add(new int[] { interval[0], interval[1] });
            } else {
                ans.get(ans.size() - 1)[1] = Math.max(ans.get(ans.size() - 1)[1], interval[1]);
            }
        }
        return ans.toArray(new int[ans.size()][]);
    }
}

// TIME COMPLEXITY = O(N log N + N)
// SPACE COMPLEXITY = O(1) auxiliary
