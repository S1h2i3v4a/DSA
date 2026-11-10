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
Priority Queue (Max-Heap): Instead of scanning all n - 1 segments linearly in each iteration, use a max-heap to fetch the segment with the largest current length in O(log N).
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    static class Section {
        double dist;
        int idx;
        Section(double dist, int idx) {
            this.dist = dist;
            this.idx = idx;
        }
    }

    public static double findSmallestMaxDist(int[] stations, int k) {
        int n = stations.length;
        int[] howMany = new int[n - 1];
        PriorityQueue<Section> pq = new PriorityQueue<>((a, b) -> Double.compare(b.dist, a.dist));

        for (int i = 0; i < n - 1; i++) {
            pq.add(new Section(stations[i + 1] - stations[i], i));
        }

        for (int gasStation = 1; gasStation <= k; gasStation++) {
            Section top = pq.poll();
            int secIdx = top.idx;
            howMany[secIdx]++;
            double initialDiff = stations[secIdx + 1] - stations[secIdx];
            double newSecLen = initialDiff / (double) (howMany[secIdx] + 1);
            pq.add(new Section(newSecLen, secIdx));
        }

        return pq.peek().dist;
    }
}

// TIME COMPLEXITY = O(K * log N)
// SPACE COMPLEXITY = O(N)
