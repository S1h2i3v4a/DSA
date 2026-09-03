/*
 * Problem Name: Search an Element in an Array
 * Platform: GeeksforGeeks
 * Difficulty: Basic
 * Time Complexity: O(N)
 * Space Complexity: O(1)
 */

class Solution {
    public static int search(int arr[], int n, int x) {
        for (int i = 0; i < n; i++) {
            if (arr[i] == x)
                return i;
        }
        return -1;
    }
}
