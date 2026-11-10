/*
QUESTION:-
We have an horizontal number line with gas stations at positions stations[0], stations[1], ..., stations[N-1].
We add K more gas stations so that D, the maximum distance between adjacent gas stations, is minimized.
Find the smallest possible value of D.

Example:
Input: stations = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], k = 9
Output: 0.50
*/

/*
APPROACH:-
Greedy Simulation: Maintain an array howMany[n-1] to count how many gas stations are inserted between adjacent pairs. In each of the k iterations, find the segment with max section length and insert 1 station there.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public static double findSmallestMaxDist(int[] stations, int k) {
        int n = stations.length;
        int[] howMany = new int[n - 1];

        for (int gasStation = 1; gasStation <= k; gasStation++) {
            double maxSection = -1;
            int maxIdx = -1;
            for (int i = 0; i < n - 1; i++) {
                double diff = stations[i + 1] - stations[i];
                double sectionLen = diff / (double) (howMany[i] + 1);
                if (sectionLen > maxSection) {
                    maxSection = sectionLen;
                    maxIdx = i;
                }
            }
            howMany[maxIdx]++;
        }

        double maxAns = -1;
        for (int i = 0; i < n - 1; i++) {
            double diff = stations[i + 1] - stations[i];
            double sectionLen = diff / (double) (howMany[i] + 1);
            maxAns = Math.max(maxAns, sectionLen);
        }
        return maxAns;
    }
}

// TIME COMPLEXITY = O(K * N)
// SPACE COMPLEXITY = O(N)
