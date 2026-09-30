/**
 * Problem Name: Minimum Platforms
 * Platform: GeeksforGeeks
 * Difficulty: Medium
 * 
 * Time Complexity: O(N log N) for sorting arrival and departure arrays
 * Space Complexity: O(1) auxiliary space (or O(N) if input arrays cannot be mutated)
 * 
 * Approach:
 * Two Pointers / Event Sorting:
 * 1. Sort arrival array arr[] and departure array dep[] independently in ascending order.
 * 2. Use two pointers i (for arr) and j (for dep).
 * 3. Iterate through both arrays:
 *    - If arr[i] <= dep[j]: a train arrives before previous leaves -> platformsNeeded++, i++.
 *    - Else (arr[i] > dep[j]): a train leaves before next arrives -> platformsNeeded--, j++.
 * 4. Maintain maxPlatforms = Math.max(maxPlatforms, platformsNeeded).
 */

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
