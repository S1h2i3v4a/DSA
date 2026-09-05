/*
 * Problem Name: Find Nth Root of an Integer
 * Platform: GeeksforGeeks
 * Difficulty: Medium
 * Time Complexity: O(n * log m)
 * Space Complexity: O(1)
 */

class Solution {
    public int NthRoot(int n, int m) {
        int low = 1, high = m;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            long val = power(mid, n, m);
            if (val == 1) return mid;
            else if (val == 0) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }

    private long power(int mid, int n, int m) {
        long ans = 1;
        for (int i = 1; i <= n; i++) {
            ans = ans * mid;
            if (ans > m) return 2;
        }
        if (ans == m) return 1;
        return 0;
    }
}
