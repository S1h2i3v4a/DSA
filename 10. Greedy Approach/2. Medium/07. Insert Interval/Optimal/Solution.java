/*
QUESTION:
Insert `newInterval` into non-overlapping sorted `intervals` in O(N) single pass.

APPROACH:
- Phase 1: Add all intervals ending before `newInterval` starts (`interval[1] < newInterval[0]`).
- Phase 2: Merge all overlapping intervals (`interval[0] <= newInterval[1]`) by updating `newInterval = [min(start), max(end)]`.
- Add merged `newInterval`.
- Phase 3: Add all remaining intervals.

TIME COMPLEXITY: O(N) single pass.
SPACE COMPLEXITY: O(N) for result list.
*/

import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int i = 0, n = intervals.length;

        // Phase 1: before overlap
        while (i < n && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }

        // Phase 2: overlap
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        result.add(newInterval);

        // Phase 3: after overlap
        while (i < n) {
            result.add(intervals[i]);
            i++;
        }

        return result.toArray(new int[result.size()][]);
    }
}
