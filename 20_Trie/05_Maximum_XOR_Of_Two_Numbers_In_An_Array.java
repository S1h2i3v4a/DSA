/**
 * Problem Name: Maximum XOR of Two Numbers in an Array
 * Platform: LeetCode (LC 421) / GeeksforGeeks
 * Difficulty: Medium
 *
 * Time Complexity: O(32 * N) = O(N) where N is array length
 * Space Complexity: O(32 * N) for 0/1 Bitwise Trie
 *
 * Approach:
 *   - Find the maximum result of nums[i] XOR nums[j] for any pair (i, j).
 *   - Bitwise 0/1 Trie:
 *     1. Insert all numbers into a Binary Trie representing their 32-bit (or 31-bit) binary form
 *        from Most Significant Bit (MSB, bit 31) to Least Significant Bit (LSB, bit 0).
 *     2. For each number x, query the Trie greedily from bit 31 down to 0:
 *        - At bit b, let the current bit of x be `bit = (x >> b) & 1`.
 *        - To maximize XOR, we desire the OPPOSITE bit: `opposite = 1 - bit`.
 *        - If a child with `opposite` exists in the Trie, move to it and set bit b in maxXOR.
 *        - Otherwise, we must move to `bit`.
 *     3. Maintain the maximum XOR achieved across all numbers.
 */

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
