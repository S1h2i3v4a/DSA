/*
QUESTION:
Given a set of N jobs where each job `i` has a deadline and profit, find the number of jobs done and the maximum profit.
Each job takes 1 unit of time and must be completed by its deadline.

APPROACH:
- Sort all jobs in descending order of profit.
- Find the maximum deadline among all jobs, say `maxDeadline`.
- Create a schedule array `slots` of size `maxDeadline + 1` initialized to -1.
- For each job:
  - Search from `job.deadline` down to 1 for the first available slot (`slots[slot] == -1`).
  - If an available slot is found, assign the job to this slot, increment job count, add profit.

TIME COMPLEXITY: O(N log N + N * maxDeadline).
SPACE COMPLEXITY: O(maxDeadline) for slots array.
*/

import java.util.Arrays;

class Job {
    int id, deadline, profit;
    Job(int id, int deadline, int profit) {
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
    }
}

class Solution {
    public int[] JobScheduling(Job[] arr, int n) {
        Arrays.sort(arr, (a, b) -> b.profit - a.profit);

        int maxDeadline = 0;
        for (Job j : arr) {
            maxDeadline = Math.max(maxDeadline, j.deadline);
        }

        int[] slots = new int[maxDeadline + 1];
        Arrays.fill(slots, -1);

        int countJobs = 0, totalProfit = 0;

        for (int i = 0; i < n; i++) {
            for (int s = arr[i].deadline; s > 0; s--) {
                if (slots[s] == -1) {
                    slots[s] = arr[i].id;
                    countJobs++;
                    totalProfit += arr[i].profit;
                    break;
                }
            }
        }
        return new int[]{countJobs, totalProfit};
    }
}
