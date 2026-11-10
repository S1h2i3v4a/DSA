/*
QUESTION:
Find maximum number of meetings accommodated in one room using Greedy Activity Selection.

APPROACH:
- Store meetings as pairs `(start, end)`.
- Sort meetings in ascending order of their end times.
- Greedily select the first meeting.
- For each subsequent meeting, if `meeting.start > lastEndTime`, select it and update `lastEndTime = meeting.end`.

TIME COMPLEXITY: O(N log N) for sorting meetings by end time.
SPACE COMPLEXITY: O(N) to store meeting pairs.
*/

import java.util.Arrays;

class Solution {
    private static class Meeting {
        int start, end;
        Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public int maxActivities(int[] start, int[] end, int n) {
        Meeting[] meetings = new Meeting[n];
        for (int i = 0; i < n; i++) {
            meetings[i] = new Meeting(start[i], end[i]);
        }

        Arrays.sort(meetings, (a, b) -> a.end - b.end);

        int count = 1;
        int lastEndTime = meetings[0].end;

        for (int i = 1; i < n; i++) {
            if (meetings[i].start > lastEndTime) {
                count++;
                lastEndTime = meetings[i].end;
            }
        }
        return count;
    }
}
