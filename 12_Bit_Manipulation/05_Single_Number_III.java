/**
 * Problem Name: Single Number III
 * Platform: LeetCode (260)
 * Difficulty: Medium
 * 
 * Time Complexity: O(N) where N is array length.
 * Space Complexity: O(1) auxiliary space.
 * 
 * Approach:
 * Bitwise XOR Partitioning.
 * 1. XOR all numbers -> `xorAll = num1 ^ num2`.
 * 2. Find rightmost set bit using two's complement mask: `rightmostBit = xorAll & (-xorAll)`.
 * 3. Divide numbers into two groups based on whether `(num & rightmostBit) != 0`.
 *    The two unique numbers will fall into separate groups, isolating both values.
 */

import java.util.Arrays;

class Single_Number_III {

    public static int[] singleNumber(int[] nums) {
        long xorAll = 0;
        for (int num : nums) {
            xorAll ^= num;
        }

        // Extract rightmost set bit
        long rightmostBit = xorAll & (-xorAll);

        int b1 = 0, b2 = 0;
        for (int num : nums) {
            if ((num & rightmostBit) != 0) {
                b1 ^= num;
            } else {
                b2 ^= num;
            }
        }

        return new int[]{b1, b2};
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 3, 2, 5};
        int[] result = singleNumber(nums);
        System.out.println("Single numbers in " + Arrays.toString(nums) + ": " + Arrays.toString(result));
    }
}
