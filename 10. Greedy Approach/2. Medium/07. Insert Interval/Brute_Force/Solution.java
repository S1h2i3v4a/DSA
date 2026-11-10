/*
QUESTION:
You are given an array of non-overlapping intervals `intervals` where `intervals[i] = [starti, endi]` sorted in ascending order by `starti`.
Insert `newInterval` into `intervals` such that `intervals` is still sorted and has no overlapping intervals.

APPROACH:
- Add `newInterval` into the list of intervals.
- Sort all intervals by their start time.
- Traverse sorted intervals and merge overlapping intervals.

TIME COMPLEXITY: O(N log N) for sorting all intervals.
SPACE COMPLEXITY: O(N) for list of merged intervals.
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> list = new ArrayList<>(Arrays.asList(intervals));
        list.add(newInterval);
        list.sort((a, b) -> a[0] - b[0]);

        List<int[]> merged = new ArrayList<>();
        int[] curr = list.get(0);

        for (int i = 1; i < list.size(); i++) {
            int[] next = list.get(i);
            if (curr[1] >= next[0]) {
                curr[1] = Math.max(curr[1], next[1]);
            } else {
                merged.add(curr);
                curr = next;
            }
        }
        merged.add(curr);
        return merged.toArray(new int[merged.size()][]);
    }
}
