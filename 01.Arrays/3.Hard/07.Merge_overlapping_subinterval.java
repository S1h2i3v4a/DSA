/*
QUESTION:
Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals and return an array of non-overlapping intervals that cover all the intervals in the input.

Example 1:
Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].

APPROACH:
To merge overlapping intervals, we can follow these steps:
1. Sort the intervals based on the start time.
2. Initialize a vector `ans` to store the merged intervals.
3. Add the first interval from the sorted intervals to the `ans` vector.
4. Iterate through the remaining intervals:
   - If the start time of the current interval is less than or equal to the end time of the last interval in the `ans` vector, it means they overlap. Update the end time of the last interval in the `ans` vector if necessary.
   - If the start time of the current interval is greater than the end time of the last interval in the `ans` vector, it means they don't overlap. Add the current interval to the `ans` vector.
5. Return the `ans` vector as the merged non-overlapping intervals.

CODE:
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) return intervals;
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> result = new ArrayList<>();
        int[] current = intervals[0];
        result.add(current);
        for (int[] interval : intervals) {
            if (interval[0] <= current[1]) {
                current[1] = Math.max(current[1], interval[1]);
            } else {
                current = interval;
                result.add(current);
            }
        }
        return result.toArray(new int[result.size()][]);
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
