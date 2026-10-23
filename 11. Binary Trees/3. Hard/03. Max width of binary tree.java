/* QUESTION:

Given the root of a binary tree, return the maximum width of the given tree.

The maximum width of a tree is the maximum width among all levels.

The width of one level is defined as the length between the end-nodes (the leftmost and rightmost non-null nodes), where the null nodes between the end-nodes that would be present in a complete binary tree extending down to that level are also counted into the length calculation.

It is guaranteed that the answer will be in the range of a 32-bit signed integer.

Example 1:
Input: root = [1,3,2,5,3,null,9]
Output: 4
Explanation: The maximum width exists in the third level with length 4 (5,3,null,9).


APPROACH:

To find the maximum width, we can perform a level-order traversal (BFS) of the binary tree while keeping track of the indices of nodes at each level. For each level, we calculate the width by finding the difference between the indices of the leftmost and rightmost non-null nodes. The maximum width among all levels will be the answer.

COMPLEXITY ANALYSIS:

Let `n` be the number of nodes in the binary tree.
- Time Complexity: The time complexity of this approach is O(n) since we need to traverse all nodes in the binary tree using BFS.
- Space Complexity: The space complexity is O(w) for the queue, where `w` is the maximum width of the binary tree at any level.

CODE:
*/

import java.io.*;
import java.util.*;


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

// CODE:-
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

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
