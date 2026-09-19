/**
 * Problem Name: Minimum Bit Flips to Convert Number
 * Platform: LeetCode (2220)
 * Difficulty: Easy
 * 
 * Time Complexity: O(1) - max 32 operations.
 * Space Complexity: O(1)
 * 
 * Approach:
 * Bitwise XOR & Brian Kernighan's Algorithm.
 * 1. Calculate XOR difference `xorVal = start ^ goal`. Each set bit in `xorVal` represents a position where bits differ.
 * 2. Count the number of set bits in `xorVal` using `xorVal & (xorVal - 1)`.
 */

class Minimum_Bit_Flips_To_Convert_Number {

    public static int minBitFlips(int start, int goal) {
        int xorVal = start ^ goal;
        int count = 0;

        while (xorVal > 0) {
            xorVal = xorVal & (xorVal - 1); // Clears the lowest set bit
            count++;
        }

        return count;
    }

    public static void main(String[] args) {
        int start = 10; // Binary: 1010
        int goal = 7;   // Binary: 0111
        System.out.println("Min bit flips to convert " + start + " to " + goal + ": " + minBitFlips(start, goal));
    }
}
