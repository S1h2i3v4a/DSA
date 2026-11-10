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
Binary Search on Answer: Distance lies in [0, max(gap)]. For a distance mid, calculate how many stations are required. If count <= k, high = mid; else low = mid. Repeat until high - low <= 1e-6.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public static double findSmallestMaxDist(int[] stations, int k) {
        double low = 0, high = 0;
        for (int i = 0; i < stations.length - 1; i++) {
            high = Math.max(high, stations[i + 1] - stations[i]);
        }
        double diff = 1e-6;

        while (high - low > diff) {
            double mid = low + (high - low) / 2.0;
            if (countGasStations(stations, mid) <= k) {
                high = mid;
            } else {
                low = mid;
            }
        }
        return high;
    }

    private static int countGasStations(int[] stations, double dist) {
        int count = 0;
        for (int i = 0; i < stations.length - 1; i++) {
            int numberInBetween = (int) ((stations[i + 1] - stations[i]) / dist);
            if ((stations[i + 1] - stations[i]) == dist * numberInBetween) {
                numberInBetween--;
            }
            count += numberInBetween;
        }
        return count;
    }
}

// TIME COMPLEXITY = O(N * log(maxDist / 1e-6))
// SPACE COMPLEXITY = O(1)
