/*
 * Problem Name: Find Square Root of a Number in O(log N)
 * Platform: LeetCode (69) / GeeksforGeeks
 * Difficulty: Easy
 * Time Complexity: O(log N)
 * Space Complexity: O(1)
 */

class Solution {
    public int mySqrt(int x) {
        if (x == 0 || x == 1) return x;
        long low = 1, high = x, ans = 1;
        while (low <= high) {
            long mid = low + (high - low) / 2;
            if (mid * mid <= x) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return (int) ans;
    }
}
