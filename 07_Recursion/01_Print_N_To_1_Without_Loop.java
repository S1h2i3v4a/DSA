/*
 * Problem Name: Print N to 1 Without Loop
 * Platform: GeeksforGeeks
 * Difficulty: Easy
 * Time Complexity: O(N)
 * Space Complexity: O(N)
 */

class Solution {
    void printNos(int N) {
        if(N==0) return;
        System.out.print(N + " ");
        printNos(N-1);
    }
}
