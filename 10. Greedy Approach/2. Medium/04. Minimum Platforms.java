/*
Question:
Given arrival and departure times of all trains that reach a railway station, find the minimum number of platforms required for the railway station so that no train is kept waiting.
Consider that all the trains arrive on the same day and leave on the same day. Arrival and departure time can never be the same for a train, but we can have the arrival time of one train equal to the departure time of another. At any given instance of time, the same platform cannot be used for both the departure of a train and the arrival of another train. In such cases, we need different platforms.

Example:
Input: n = 6
arr[] = {0900, 0940, 0950, 1100, 1500, 1800}
dep[] = {0910, 1200, 1120, 1130, 1900, 2000}
Output: 3
Explanation:
Minimum 3 platforms are required to safely arrive and depart all trains.

Approach:
- We sort the arrival and departure arrays in non-decreasing order.
- We initialize variables 'i', 'j', 'plat', and 'ans' to 0.
- We iterate over the arrival array using 'i' and departure array using 'j'.
- If the arrival time at index 'i' is less than or equal to the departure time at index 'j', it means a train is arriving and we need an additional platform.
  - So, we increment 'i' and 'plat'.
  - We update 'ans' with the maximum value of 'ans' and 'plat'.
- If the arrival time is greater than the departure time, it means a train has departed and we can free up a platform.
  - So, we increment 'j' and decrement 'plat'.
- Finally, we return 'ans' as the minimum number of platforms required.

Complexity Analysis:
- The time complexity of this approach is O(NlogN), where N is the number of trains.
- We sort the arrival and departure arrays, which takes O(NlogN) time.
- The space complexity is O(1) as we use only a constant amount of extra space.

Code:
*/

import java.util.*;
import java.io.*;

// CODE:-
import java.util.*;

class Minimum_Platforms {
    public int findPlatform(int[] arr, int[] dep, int n) {
        Arrays.sort(arr);
        Arrays.sort(dep);

        int platformsNeeded = 0;
        int maxPlatforms = 0;

        int i = 0; // Pointer for arrival
        int j = 0; // Pointer for departure

        while (i < n && j < n) {
            if (arr[i] <= dep[j]) {
                platformsNeeded++;
                i++;
            } else {
                platformsNeeded--;
                j++;
            }
            maxPlatforms = Math.max(maxPlatforms, platformsNeeded);
        }

        return maxPlatforms;
    }

    public static void main(String[] args) {
        Minimum_Platforms solver = new Minimum_Platforms();
        int[] arr = {900, 940, 950, 1100, 1500, 1800};
        int[] dep = {910, 1200, 1120, 1130, 1900, 2000};
        int n = arr.length;
        System.out.println("Minimum platforms required: " + solver.findPlatform(arr, dep, n)); // Expected: 3
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
