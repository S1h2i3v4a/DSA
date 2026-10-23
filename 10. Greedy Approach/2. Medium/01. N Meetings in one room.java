/*
Question:
There is one meeting room in a firm. There are N meetings in the form of (start[i], end[i]) where start[i] is the start time of meeting i and end[i] is the finish time of meeting i.
What is the maximum number of meetings that can be accommodated in the meeting room when only one meeting can be held in the meeting room at a particular time?
Note: Start time of one chosen meeting can't be equal to the end time of the other chosen meeting.

Example:
Input:
N = 6
start[] = {1,3,0,5,8,5}
end[] = {2,4,6,7,9,9}
Output: 4
Explanation: Maximum four meetings can be held with the given start and end timings.
The meetings are - (1, 2), (3, 4), (5, 7), and (8, 9)

Approach:
- We store the meetings as pairs of (end[i], start[i]) in a vector.
- We sort the vector in non-decreasing order based on the end time of meetings.
- We initialize the answer as 1 and the previous meeting end time as the end time of the first meeting in the sorted vector.
- We iterate over the meetings starting from the second meeting.
  - If the start time of the current meeting is greater than the previous meeting end time, we increment the answer and update the previous meeting end time.
- Finally, we return the answer.

Complexity Analysis:
- The time complexity of this approach is O(NlogN), where N is the number of meetings.
- Sorting the meetings based on the end time takes O(NlogN) time.
- The space complexity is O(N) as we store the meetings in a vector.

Code:
*/

import java.util.*;
import java.io.*;

// CODE:-
import java.util.*;

class Activity_Selection {
    static class Activity {
        int start;
        int finish;

        Activity(int start, int finish) {
            this.start = start;
            this.finish = finish;
        }
    }

    public int maxActivities(int[] start, int[] end, int n) {
        Activity[] arr = new Activity[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new Activity(start[i], end[i]);
        }

        // Sort activities by finish time ascending
        Arrays.sort(arr, (a, b) -> Integer.compare(a.finish, b.finish));

        int count = 1;
        int lastFinish = arr[0].finish;

        for (int i = 1; i < n; i++) {
            if (arr[i].start > lastFinish) {
                count++;
                lastFinish = arr[i].finish;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Activity_Selection solver = new Activity_Selection();
        int[] start = {1, 3, 0, 5, 8, 5};
        int[] end = {2, 4, 6, 7, 9, 9};
        int n = start.length;
        System.out.println("Maximum non-overlapping activities: " + solver.maxActivities(start, end, n)); // Expected: 4
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
