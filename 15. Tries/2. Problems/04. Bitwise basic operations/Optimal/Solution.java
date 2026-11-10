/*
QUESTION:
Implement getXOR(a, b), getBit(c, d), and setBit(e, f).

APPROACH:
- getXOR: bitwise XOR operator (^).
- getBit: right shift d by c and bitwise AND with 1: (d >> c) & 1.
- setBit: bitwise OR f with (1 << e): f | (1 << e).

TIME COMPLEXITY: O(1) direct hardware bitwise operations.
SPACE COMPLEXITY: O(1) no extra memory.
*/

class Solution {
    public static int getXOR(int a, int b) {
        return a ^ b;
    }

    public static int getBit(int c, int d) {
        return (d >> c) & 1;
    }

    public static int setBit(int e, int f) {
        return f | (1 << e);
    }
}
