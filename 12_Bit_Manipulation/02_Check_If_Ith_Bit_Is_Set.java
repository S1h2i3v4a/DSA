/**
 * Problem Name: Check if K-th / I-th Bit is Set or Not
 * Platform: GeeksforGeeks
 * Difficulty: Easy
 * 
 * Time Complexity: O(1)
 * Space Complexity: O(1)
 * 
 * Approach:
 * Bitwise Operations.
 * Method 1: Left Shift 1 by K bits -> (1 << K). Perform Bitwise AND with N: `(n & (1 << k)) != 0`.
 * Method 2: Right Shift N by K bits -> (N >> K). Perform Bitwise AND with 1: `((n >> k) & 1) == 1`.
 */

class Check_If_Ith_Bit_Is_Set {

    public static boolean checkKthBitLeftShift(int n, int k) {
        return (n & (1 << k)) != 0;
    }

    public static boolean checkKthBitRightShift(int n, int k) {
        return ((n >> k) & 1) == 1;
    }

    public static void main(String[] args) {
        int n = 5; // Binary: 101
        int k = 2; // 0-indexed: bit 2 is '1'
        System.out.println("Is bit " + k + " set in " + n + " (Left Shift)? " + checkKthBitLeftShift(n, k));
        System.out.println("Is bit " + k + " set in " + n + " (Right Shift)? " + checkKthBitRightShift(n, k));
    }
}
