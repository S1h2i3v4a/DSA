/*
QUESTION:
Calculate the least number of units of time to finish all tasks with cooldown `n` in O(tasks.length).

APPROACH:
- Find the maximum frequency `maxFreq` among all tasks.
- Count how many tasks share this maximum frequency (`countMaxFreq`).
- The task with maximum frequency creates `(maxFreq - 1)` frames of size `(n + 1)`.
- Minimum time is `(maxFreq - 1) * (n + 1) + countMaxFreq`.
- It cannot be less than `tasks.length`.

TIME COMPLEXITY: O(tasks.length) to count frequencies.
SPACE COMPLEXITY: O(26) = O(1) constant auxiliary space.
*/

class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] freq = new int[26];
        int maxFreq = 0;
        for (char c : tasks) {
            freq[c - 'A']++;
            maxFreq = Math.max(maxFreq, freq[c - 'A']);
        }

        int countMaxFreq = 0;
        for (int f : freq) {
            if (f == maxFreq) countMaxFreq++;
        }

        int intervals = (maxFreq - 1) * (n + 1) + countMaxFreq;
        return Math.max(tasks.length, intervals);
    }
}
