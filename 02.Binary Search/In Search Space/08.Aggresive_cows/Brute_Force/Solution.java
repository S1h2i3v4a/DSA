/*
QUESTION:-
You are given an array of integers stalls which represents the locations of stalls, and an integer k which represents the number of aggressive cows.
Assign stalls to k cows such that the minimum distance between any two cows is as large as possible. Return that largest minimum distance.

Example:
Input: stalls = [1, 2, 8, 4, 9], k = 3
Output: 3
Explanation: Cows can be placed at stalls 1, 4, 8 with minimum distance 3.
*/

/*
APPROACH:-
Linear Search: Sort stalls. The minimum distance ranges from 1 to stalls[n-1] - stalls[0]. Linearly check every distance d: return the maximum distance where k cows can be placed.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int aggressiveCows(int[] stalls, int k) {
        Arrays.sort(stalls);
        int maxDist = stalls[stalls.length - 1] - stalls[0];
        int ans = 1;
        for (int d = 1; d <= maxDist; d++) {
            if (canPlace(stalls, k, d)) {
                ans = d;
            } else {
                break;
            }
        }
        return ans;
    }

    private boolean canPlace(int[] stalls, int k, int dist) {
        int countCows = 1, last = stalls[0];
        for (int i = 1; i < stalls.length; i++) {
            if (stalls[i] - last >= dist) {
                countCows++;
                last = stalls[i];
            }
            if (countCows >= k) return true;
        }
        return false;
    }
}

// TIME COMPLEXITY = O((max - min) * N)
// SPACE COMPLEXITY = O(1)
