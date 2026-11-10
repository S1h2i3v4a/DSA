/*
QUESTION:
Find minimum number of railway platforms in O(N log N) using sorting and two pointers.

APPROACH:
- Sort arrival times array `arr` and departure times array `dep` separately.
- Maintain two pointers: `i = 0` (arrivals) and `j = 0` (departures).
- If `arr[i] <= dep[j]`: train arrives before another departs -> need one more platform (`count++`, `i++`).
- Else: a train has departed -> platform freed (`count--`, `j++`).
- Track maximum `count` seen.

TIME COMPLEXITY: O(N log N) to sort both arrival and departure arrays.
SPACE COMPLEXITY: O(1) constant auxiliary space.
*/

import java.util.Arrays;

class Solution {
    public int findPlatform(int[] arr, int[] dep, int n) {
        Arrays.sort(arr);
        Arrays.sort(dep);

        int platforms = 0, maxPlatforms = 0;
        int i = 0, j = 0;

        while (i < n && j < n) {
            if (arr[i] <= dep[j]) {
                platforms++;
                maxPlatforms = Math.max(maxPlatforms, platforms);
                i++;
            } else {
                platforms--;
                j++;
            }
        }
        return maxPlatforms;
    }
}
