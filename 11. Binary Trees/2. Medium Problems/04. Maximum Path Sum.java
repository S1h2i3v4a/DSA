/*
Question:
A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them.
A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.
The path sum of a path is the sum of the node's values in the path.
Given the root of a binary tree, return the maximum path sum of any non-empty path.

Approach:
- The maximum path sum can be calculated using a recursive approach.
- For each node, we calculate the maximum path sum that includes the node as the root.
- The maximum path sum that includes the current node is the maximum of the following values:
  - The value of the current node.
  - The maximum path sum of the left subtree plus the value of the current node.
  - The maximum path sum of the right subtree plus the value of the current node.
  - The maximum path sum of the left subtree plus the maximum path sum of the right subtree plus the value of the current node.
- During the calculation, we keep track of the maximum path sum encountered so far and update it if necessary.
- We return the maximum path sum encountered overall.

Complexity Analysis:
- Since we visit each node once and perform constant time operations for each node, the time complexity of this approach is O(N), where N is the number of nodes in the binary tree.
- The space complexity is O(H), where H is the height of the binary tree. In the worst case, the tree can be skewed and have a height of N, resulting in O(N) space complexity. In the best case, the tree is balanced and has a height of log(N), resulting in O(log(N)) space complexity.

Code:
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
class Binary_Tree_Maximum_Path_Sum {
    private int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        maxSum = Integer.MIN_VALUE;
        maxGain(root);
        return maxSum;
    }

    private int maxGain(TreeNode node) {
        if (node == null) return 0;

        // Ignore negative path sums by using Math.max(0, ...)
        int leftGain = Math.max(0, maxGain(node.left));
        int rightGain = Math.max(0, maxGain(node.right));

        int currentPathSum = node.val + leftGain + rightGain;
        maxSum = Math.max(maxSum, currentPathSum);

        return node.val + Math.max(leftGain, rightGain);
    }

    public static void main(String[] args) {
        Binary_Tree_Maximum_Path_Sum solver = new Binary_Tree_Maximum_Path_Sum();
        // Tree: -10 -> (9, 20 -> (15, 7))
        TreeNode root = new TreeNode(-10);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        System.out.println("Maximum Path Sum: " + solver.maxPathSum(root)); // Expected: 42 (15 + 20 + 7)
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
