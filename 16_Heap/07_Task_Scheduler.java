/**
 * Problem Name: Task Scheduler
 * Platform: LeetCode (LC 621)
 * Difficulty: Medium
 * 
 * Time Complexity: O(N) where N is total number of tasks
 * Space Complexity: O(1) as frequency array size is fixed at 26
 * 
 * Approach:
 * Count the frequency of each task. Find the maximum frequency maxFreq, and count
 * how many tasks have this maximum frequency (maxFreqCount).
 * The minimum time required is dictated by the idle slots between the most frequent tasks:
 * Math.max(tasks.length, (maxFreq - 1) * (n + 1) + maxFreqCount).
 */

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
