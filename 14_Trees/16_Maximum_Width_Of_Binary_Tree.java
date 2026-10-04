/**
 * Problem Name: Maximum Width of Binary Tree
 * Platform: LeetCode (LC 662)
 * Difficulty: Medium
 * 
 * Time Complexity: O(N) where N is the total number of nodes
 * Space Complexity: O(N) for BFS queue
 * 
 * Approach:
 * BFS with Zero-Indexed Node Numbering:
 * For any node at index i:
 *   - Left child is at index (2 * i + 1)
 *   - Right child is at index (2 * i + 2)
 * To avoid integer overflow on deep/skewed trees, subtract the minimum index of each level (minId)
 * before computing child indices.
 * At each level, width = lastIndex - firstIndex + 1.
 * Track maxWidth = Math.max(maxWidth, width).
 */

import java.util.*;

class Maximum_Width_Of_Binary_Tree {
    private static class Pair {
        TreeNode node;
        long index;

        Pair(TreeNode node, long index) {
            this.node = node;
            this.index = index;
        }
    }

    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;

        int maxWidth = 0;
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(root, 0));

        while (!queue.isEmpty()) {
            int size = queue.size();
            long minId = queue.peek().index; // First index of the current level
            long first = 0, last = 0;

            for (int i = 0; i < size; i++) {
                Pair curr = queue.poll();
                long currId = curr.index - minId; // Normalize to prevent overflow

                if (i == 0) first = currId;
                if (i == size - 1) last = currId;

                if (curr.node.left != null) {
                    queue.offer(new Pair(curr.node.left, 2 * currId + 1));
                }
                if (curr.node.right != null) {
                    queue.offer(new Pair(curr.node.right, 2 * currId + 2));
                }
            }

            maxWidth = Math.max(maxWidth, (int) (last - first + 1));
        }

        return maxWidth;
    }

    public static void main(String[] args) {
        Maximum_Width_Of_Binary_Tree solver = new Maximum_Width_Of_Binary_Tree();
        // Tree: 1 -> (3 -> (5, 3), 2 -> (null, 9))
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(9);

        System.out.println("Maximum Width: " + solver.widthOfBinaryTree(root)); // Expected: 4
    }
}
