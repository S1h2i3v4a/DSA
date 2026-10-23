/*
Question:
Given a binary tree, determine if it is height-balanced.

Approach:
- We can solve this problem recursively by checking if the left and right subtrees of each node are height-balanced.
- For each node, we calculate the height of its left and right subtrees, and check if the absolute difference of their heights is at most 1.
- If both subtrees are height-balanced, and the absolute difference of their heights is at most 1, then the current node and its subtree are height-balanced.
- We can use a pair of values to represent the result of the recursion: the first value indicates if the subtree is height-balanced, and the second value represents the height of the subtree.

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
class Balanced_Binary_Tree {
    public boolean isBalanced(TreeNode root) {
        return checkHeight(root) != -1;
    }

    private int checkHeight(TreeNode node) {
        if (node == null) return 0;

        int leftHeight = checkHeight(node.left);
        if (leftHeight == -1) return -1;

        int rightHeight = checkHeight(node.right);
        if (rightHeight == -1) return -1;

        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }

        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static void main(String[] args) {
        Balanced_Binary_Tree solver = new Balanced_Binary_Tree();
        // Balanced Tree: 3 -> (9, 20 -> (15, 7))
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(9);
        root1.right = new TreeNode(20);
        root1.right.left = new TreeNode(15);
        root1.right.right = new TreeNode(7);
        System.out.println("Is root1 balanced: " + solver.isBalanced(root1)); // true

        // Unbalanced Tree: 1 -> (2 -> (3 -> (4, 4), 3), 2)
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.left.left = new TreeNode(3);
        root2.left.left.left = new TreeNode(4);
        System.out.println("Is root2 balanced: " + solver.isBalanced(root2)); // false
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
