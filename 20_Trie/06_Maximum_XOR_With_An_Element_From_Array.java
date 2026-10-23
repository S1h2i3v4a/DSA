/**
 * Problem Name: Maximum XOR With an Element From Array
 * Platform: LeetCode (LC 1707) / GeeksforGeeks
 * Difficulty: Hard
 *
 * Time Complexity: O(N * log N + Q * log Q + (N + Q) * 32)
 * Space Complexity: O(N * 32 + Q) for Offline Query sorting and Binary Trie
 *
 * Approach:
 *   - You are given array `nums` and queries where queries[i] = [x_i, m_i].
 *   - The answer is the maximum value of nums[j] XOR x_i such that nums[j] <= m_i.
 *     If no such element exists, return -1.
 *   - Offline Query Processing with Binary Trie:
 *     1. Sort `nums` in ascending order.
 *     2. Attach original indices to each query: (x, m, originalIndex), and sort queries by `m` ascendingly.
 *     3. Maintain a pointer `idx` into `nums` and an empty Binary Trie:
 *        For each sorted query (x, m, qIdx):
 *          - Incrementally insert all nums[idx] into the Trie where nums[idx] <= m.
 *          - If no elements have been inserted into the Trie (idx == 0), ans[qIdx] = -1.
 *          - Otherwise, query the Trie for maxXOR with x, and assign to ans[qIdx].
 *     4. Return the results in original query order.
 */

import java.util.Arrays;

class MaximumXORWithAnElementFromArray {

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

    public static int[] maximizeXor(int[] nums, int[][] queries) {
        Arrays.sort(nums);
        int qLen = queries.length;
        int[][] sortedQueries = new int[qLen][3];

        for (int i = 0; i < qLen; i++) {
            sortedQueries[i][0] = queries[i][0]; // x
            sortedQueries[i][1] = queries[i][1]; // m
            sortedQueries[i][2] = i;              // original index
        }

        // Sort queries by m ascending
        Arrays.sort(sortedQueries, (a, b) -> Integer.compare(a[1], b[1]));

        int[] result = new int[qLen];
        BinaryTrie trie = new BinaryTrie();
        int n = nums.length;
        int idx = 0;

        for (int[] query : sortedQueries) {
            int x = query[0];
            int m = query[1];
            int qIdx = query[2];

            // Add all numbers <= m into the Trie
            while (idx < n && nums[idx] <= m) {
                trie.insert(nums[idx]);
                idx++;
            }

            if (idx == 0) {
                result[qIdx] = -1; // No numbers <= m
            } else {
                result[qIdx] = trie.getMaxXOR(x);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // Test Case 1: nums = [0,1,2,3,4], queries = [[3,1],[1,3],[5,6]] -> Expected: [3, 3, 7]
        int[] nums1 = {0, 1, 2, 3, 4};
        int[][] q1 = {{3, 1}, {1, 3}, {5, 6}};
        System.out.println("Test 1: " + Arrays.toString(maximizeXor(nums1, q1))); // [3, 3, 7]

        // Test Case 2: nums = [5,2,4,6,6,3], queries = [[12,4],[8,1],[6,3]] -> Expected: [15, -1, 5]
        int[] nums2 = {5, 2, 4, 6, 6, 3};
        int[][] q2 = {{12, 4}, {8, 1}, {6, 3}};
        System.out.println("Test 2: " + Arrays.toString(maximizeXor(nums2, q2))); // [15, -1, 5]
    }
}
