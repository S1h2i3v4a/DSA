/*
 * Problem Name: Minimize Max Distance to Gas Station
 * Platform: LeetCode (774) / GeeksforGeeks
 * Difficulty: Hard
 * Time Complexity: O(N * log(high - low))
 * Space Complexity: O(1)
 */

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
            count += (int) ((stations[i + 1] - stations[i]) / dist);
        }
        return count;
    }
}
