/*
QUESTION:
There is one meeting room in a firm. There are `n` meetings in the form of `(start[i], end[i])`.
Find the maximum number of meetings that can be accommodated in the meeting room, when only one meeting can be held at a time.

APPROACH:
- Recursive backtracking: at each meeting, try including it (if it doesn't conflict with previously chosen meeting) or excluding it.
- Track maximum meetings possible.

TIME COMPLEXITY: O(2^N) exponential combinations.
SPACE COMPLEXITY: O(N) recursion stack.
*/

class Solution {
    private static class Meeting {
        int start, end;
        Meeting(int s, int e) { start = s; end = e; }
    }

    private int solve(Meeting[] meetings, int idx, int lastEnd) {
        if (idx == meetings.length) return 0;

        // Option 1: Do not pick
        int notPick = solve(meetings, idx + 1, lastEnd);

        // Option 2: Pick if non-conflicting
        int pick = 0;
        if (meetings[idx].start > lastEnd) {
            pick = 1 + solve(meetings, idx + 1, meetings[idx].end);
        }

        return Math.max(pick, notPick);
    }

    public int maxActivities(int[] start, int[] end, int n) {
        Meeting[] meetings = new Meeting[n];
        for (int i = 0; i < n; i++) meetings[i] = new Meeting(start[i], end[i]);
        return solve(meetings, 0, -1);
    }
}
