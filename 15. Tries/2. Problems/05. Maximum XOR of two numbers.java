/*
QUESTION:
Given an integer array nums, return the maximum result of nums[i] XOR nums[j], where 0 <= i <= j < n.

Example:

Input: nums = [3,10,5,25,2,8]
Output: 28
Explanation: The maximum result is 5 XOR 25 = 28.

Approach:
1. Create a Trie data structure that stores binary representations of the given numbers.
2. For each number, insert its binary representation into the Trie.
3. For each number, find the maximum XOR that can be obtained with any other number using the Trie.
   - Traverse the Trie for each bit of the number and try to find a bit in the other number's binary representation that gives the maximum XOR.
   - If a complementary bit is found, set the corresponding bit in the result.
4. Return the maximum XOR found.

Complexity Analysis:
- The Trie insertion and search operations both take O(32) time since we are dealing with 32 bits (maximum). Therefore, the time complexity of the solution is O(n * 32), which simplifies to O(n).
- The space complexity is also O(n) since we are storing the Trie.

CODE:
*/

import java.io.*;
import java.util.*;

// CODE:-
class MaximumXOROfTwoNumbersInAnArray {

    static class Node {
        Node[] children = new Node[2];
    }

    static class BinaryTrie {
        Node root = new Node();

        public void insert(int num) {
            Node node = root;
            for (int i = 31; i >= 0; i--) {
                int bit = (num >> i) & 1;
                if (node.children[bit] == null) {
                    node.children[bit] = new Node();
                }
                node = node.children[bit];
            }
        }

        public int getMaxXOR(int num) {
            Node node = root;
            int maxXor = 0;
            for (int i = 31; i >= 0; i--) {
                int bit = (num >> i) & 1;
                int oppositeBit = 1 - bit;
                if (node.children[oppositeBit] != null) {
                    maxXor |= (1 << i);
                    node = node.children[oppositeBit];
                } else {
                    node = node.children[bit];
                }
            }
            return maxXor;
        }
    }

    public static int findMaximumXOR(int[] nums) {
        BinaryTrie trie = new BinaryTrie();
        for (int x : nums) {
            trie.insert(x);
        }

        int maxResult = 0;
        for (int x : nums) {
            maxResult = Math.max(maxResult, trie.getMaxXOR(x));
        }

        return maxResult;
    }

    public static void main(String[] args) {
        // Test Case 1: nums = [3,10,5,25,2,8] -> Expected: 28 (5 XOR 25 = 28)
        int[] nums1 = {3, 10, 5, 25, 2, 8};
        System.out.println("Test 1: " + findMaximumXOR(nums1)); // 28

        // Test Case 2: nums = [14,70,53,83,49,91,36,80,92,51,66,70] -> Expected: 127
        int[] nums2 = {14, 70, 53, 83, 49, 91, 36, 80, 92, 51, 66, 70};
        System.out.println("Test 2: " + findMaximumXOR(nums2)); // 127
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
