/*
Question:
Given a set of N jobs where each job 'i' has a deadline and profit associated with it. Each job takes 1 unit of time to complete, and only one job can be scheduled at a time. We earn the profit associated with a job if and only if the job is completed by its deadline. Find the number of jobs done and the maximum profit.

Example:
Input:
N = 4
Jobs = {(1,4,20),(2,1,10),(3,1,40),(4,1,30)}
Output:
2 60
Explanation:
Job1 and Job3 can be done with a maximum profit of 60 (20+40).

Approach:
- We create a vector of pairs 'jobs' to store the profit and deadline of each job.
- We sort the 'jobs' vector in non-increasing order of profits.
- We initialize a vector 'deadline' of size 'n+1' and set all elements to -1.
- We also initialize variables 'ans' and 'cnt' to 0.
- We iterate over the 'jobs' vector.
  - For each job, we get the deadline and profit.
  - We check from the deadline to 1 (in reverse order) if there is any slot available to schedule the job.
  - If we find an available slot, we mark it as scheduled by setting 'deadline[dead]' to 1, increment 'cnt' and add the profit to 'ans'.
- Finally, we return a vector containing 'cnt' and 'ans' as the number of jobs done and the maximum profit.

Complexity Analysis:
- The time complexity of this approach is O(NlogN), where N is the number of jobs.
- This is due to the sorting operation on the 'jobs' vector based on profits.
- The space complexity is O(N) as we use additional vectors to store the jobs and deadlines.

Code:
*/

import java.util.*;
import java.io.*;

// CODE:-
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

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
