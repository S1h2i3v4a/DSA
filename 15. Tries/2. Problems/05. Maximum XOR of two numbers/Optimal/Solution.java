/*
QUESTION:
Given integer array nums, return maximum result of nums[i] XOR nums[j].

APPROACH:
- Binary Trie:
  - Insert 32-bit binary representation of each number.
  - To maximize XOR with num, at each bit try to take the opposite bit (1 - bit) if present.
  - If opposite bit exists, set that bit in result and branch to that child; else branch to same bit.

TIME COMPLEXITY: O(N * 32) = O(N).
SPACE COMPLEXITY: O(N * 32) Binary Trie nodes.
*/

class Solution {
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
}
