/*
QUESTION:
Given a characters array `tasks`, representing the tasks a CPU needs to do, and a non-negative integer `n` cooldown period,
return the least number of units of times that the CPU will take to finish all the given tasks.

APPROACH:
- Use a Max-Heap to always pick the task with the highest remaining frequency.
- Maintain a cooling queue of pairs `(remainingFrequency, availableTime)`.
- Step through time `time = 1, 2, 3...`:
  - If queue has tasks whose cooldown expired (`availableTime <= time`), re-add to max heap.
  - If heap non-empty, poll most frequent task, decrement frequency, and if still > 0, enqueue with `time + n + 1`.
  - Increment time until both heap and queue are empty.

TIME COMPLEXITY: O(total intervals * log 26) = O(total intervals).
SPACE COMPLEXITY: O(26) = O(1) for heap and queue.
*/

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] freq = new int[26];
        for (char c : tasks) freq[c - 'A']++;

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int f : freq) {
            if (f > 0) maxHeap.offer(f);
        }

        Queue<int[]> queue = new ArrayDeque<>();
        int time = 0;

        while (!maxHeap.isEmpty() || !queue.isEmpty()) {
            time++;
            if (!maxHeap.isEmpty()) {
                int count = maxHeap.poll() - 1;
                if (count > 0) {
                    queue.offer(new int[]{count, time + n});
                }
            }

            if (!queue.isEmpty() && queue.peek()[1] == time) {
                maxHeap.offer(queue.poll()[0]);
            }
        }
        return time;
    }
}
