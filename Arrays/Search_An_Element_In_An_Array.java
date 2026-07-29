/*
 * Problem Name: Search an Element in an Array
 * Platform: GeeksforGeeks
 * Difficulty: Basic
 * Time Complexity: O(N)
 * Space Complexity: O(1)
 */

class Solution {
    // User function Template for C

    public static int search(int arr[], int n, int x) {
        // Iterate over the array elements
        for (int i = 0; i < n; i++) {
            // Check if the current element is equal to 'x'
            if (arr[i] == x)
                return i; // Return the index if element is found
        }
        return -1; // Return -1 if element is not found
    }
}
