/*
 * Problem Name: Sum of First N Terms
 * Platform: GeeksforGeeks
 * Difficulty: Easy
 * Time Complexity: O(N)
 * Space Complexity: O(N)
 */

class Solution {
    int sumOfSeries(int n) {
        if(n==0 || n<0) return 0;
        return ((n*n*n) + sumOfSeries(n-1));
    }
}
