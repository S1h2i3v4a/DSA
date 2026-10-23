/*
Question:
Given a binary tree. Find the size of its largest subtree that is a Binary Search Tree.

Explanation:
- We use a recursive function to traverse the binary tree in a bottom-up manner.
- At each node, we check if the left and right subtrees are binary search trees.
- If the current node satisfies the binary search tree condition (i.e., the value of the current node is greater than the maximum value in the left subtree and less than the minimum value in the right subtree), then we update the size of the largest subtree.
- We return a triplet containing the size of the subtree, the minimum value in the subtree, and the maximum value in the subtree.

Time Complexity:
- The recursive function visits each node once, so the time complexity is O(n), where n is the number of nodes in the binary tree.

Space Complexity:
- The space complexity is O(h), where h is the height of the binary tree, as the recursion stack stores the nodes in the path from the root to the deepest leaf node.

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
class Maximum_Sum_BST_In_Binary_Tree {
    private static class NodeValue {
        int maxVal;
        int minVal;
        int sum;
        boolean isBST;

        NodeValue(int minVal, int maxVal, int sum, boolean isBST) {
            this.minVal = minVal;
            this.maxVal = maxVal;
            this.sum = sum;
            this.isBST = isBST;
        }
    }

    private int maxSum = 0;

    public int maxSumBST(TreeNode root) {
        maxSum = 0;
        traverse(root);
        return maxSum;
    }

    private NodeValue traverse(TreeNode node) {
        if (node == null) {
            return new NodeValue(Integer.MAX_VALUE, Integer.MIN_VALUE, 0, true);
        }

        NodeValue left = traverse(node.left);
        NodeValue right = traverse(node.right);

        // Check if current subtree is a valid BST
        if (left.isBST && right.isBST && left.maxVal < node.val && node.val < right.minVal) {
            int currentSum = node.val + left.sum + right.sum;
            maxSum = Math.max(maxSum, currentSum);

            int minVal = Math.min(node.val, left.minVal);
            int maxVal = Math.max(node.val, right.maxVal);
            return new NodeValue(minVal, maxVal, currentSum, true);
        }

        return new NodeValue(0, 0, 0, false);
    }

    public static void main(String[] args) {
        Maximum_Sum_BST_In_Binary_Tree solver = new Maximum_Sum_BST_In_Binary_Tree();
        // Tree: 1 -> (4 -> (2, 4), 3 -> (2, 5 -> (null, 6)))
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(4);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(2);
        root.right.right = new TreeNode(5);
        root.right.right.right = new TreeNode(6);

        System.out.println("Maximum Sum BST: " + solver.maxSumBST(root)); // Expected: 20
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
