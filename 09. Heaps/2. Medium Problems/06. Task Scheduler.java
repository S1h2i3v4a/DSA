/*
QUESTION:
Given a characters array tasks, representing the tasks a CPU needs to do, where each letter represents a different task. Tasks could be done in any order. Each task is done in one unit of time. For each unit of time, the CPU could complete either one task or just be idle.

APPROACH:
To minimize the total time, we need to consider the task with the maximum frequency. Let's assume the maximum frequency is maxfreq. The CPU will need at least (maxfreq - 1) intervals of cooldown time between the executions of the tasks with the maximum frequency.

1. Create a frequency map to count the occurrences of each task.
2. Find the maximum frequency maxfreq.
3. Calculate the total number of intervals required by multiplying (maxfreq - 1) with the cooldown period n.
4. Iterate through the frequency map and count the number of tasks with the maximum frequency. Add this count to the total number of intervals.
5. Return the maximum of the total number of intervals and the total number of tasks.

The leastInterval function implements this approach.

TIME COMPLEXITY:
The time complexity of the leastInterval function is O(N), where N is the number of tasks. We iterate through the tasks twice: once to calculate the frequencies and find the maximum frequency, and once to count the number of tasks with the maximum frequency. Both iterations take O(N) time.

SPACE COMPLEXITY:
The space complexity is O(1) because the frequency map has a fixed number of unique tasks (26 lowercase letters).
*/

import java.util.*;
import java.io.*;

// CODE:-
import java.util.*;

class Task_Scheduler {
    public int leastInterval(char[] tasks, int n) {
        int[] freq = new int[26];
        for (char task : tasks) {
            freq[task - 'A']++;
        }

        int maxFreq = 0;
        for (int f : freq) {
            maxFreq = Math.max(maxFreq, f);
        }

        int maxFreqCount = 0;
        for (int f : freq) {
            if (f == maxFreq) {
                maxFreqCount++;
            }
        }

        int ans = (maxFreq - 1) * (n + 1) + maxFreqCount;
        return Math.max(tasks.length, ans);
    }

    public static void main(String[] args) {
        Task_Scheduler solver = new Task_Scheduler();
        char[] tasks = {'A', 'A', 'A', 'B', 'B', 'B'};
        int n = 2;
        System.out.println("Least intervals needed: " + solver.leastInterval(tasks, n)); // Expected: 8
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
