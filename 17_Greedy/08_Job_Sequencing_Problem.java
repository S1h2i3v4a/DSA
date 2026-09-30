/**
 * Problem Name: Job Sequencing Problem
 * Platform: GeeksforGeeks
 * Difficulty: Medium
 * 
 * Time Complexity: O(N log N + N * maxDeadline) with slot array (or O(N log N + N * alpha(D)) with DSU)
 * Space Complexity: O(maxDeadline) for slot array
 * 
 * Approach:
 * Greedy Scheduling:
 * 1. Sort jobs in descending order of profit.
 * 2. Find maximum deadline across all jobs to size the slot array.
 * 3. Iterate through sorted jobs. For each job, try to schedule it at the latest possible
 *    available slot (slot <= deadline).
 * 4. Keep track of total count of scheduled jobs and total max profit.
 */

import java.util.*;

class Job_Sequencing_Problem {
    static class Job {
        int id;
        int deadline;
        int profit;

        Job(int id, int deadline, int profit) {
            this.id = id;
            this.deadline = deadline;
            this.profit = profit;
        }
    }

    public int[] JobScheduling(Job[] arr, int n) {
        // Sort jobs by profit descending
        Arrays.sort(arr, (a, b) -> Integer.compare(b.profit, a.profit));

        int maxDeadline = 0;
        for (int i = 0; i < n; i++) {
            maxDeadline = Math.max(maxDeadline, arr[i].deadline);
        }

        int[] result = new int[maxDeadline + 1];
        Arrays.fill(result, -1);

        int countJobs = 0;
        int maxProfit = 0;

        for (int i = 0; i < n; i++) {
            for (int j = arr[i].deadline; j > 0; j--) {
                if (result[j] == -1) {
                    result[j] = arr[i].id;
                    countJobs++;
                    maxProfit += arr[i].profit;
                    break;
                }
            }
        }

        return new int[]{countJobs, maxProfit};
    }

    public static void main(String[] args) {
        Job_Sequencing_Problem solver = new Job_Sequencing_Problem();
        Job[] arr = {
            new Job(1, 4, 20),
            new Job(2, 1, 10),
            new Job(3, 1, 40),
            new Job(4, 1, 30)
        };
        int n = arr.length;
        int[] ans = solver.JobScheduling(arr, n);
        System.out.println("Jobs scheduled: " + ans[0] + ", Total profit: " + ans[1]); // Expected: [2, 60] (Job 3 and Job 1)
    }
}
