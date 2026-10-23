/*
Question:
Given the root of a binary tree, return the length of the diameter of the tree.

Approach:
- The diameter of a binary tree is the length of the longest path between any two nodes in the tree.
- This path may or may not pass through the root.
- To find the diameter of the tree, we can recursively calculate the height of each node's left and right subtrees.
- At each node, we calculate the diameter as the maximum of the following three values:
  - The diameter of the left subtree.
  - The diameter of the right subtree.
  - The sum of the heights of the left and right subtrees plus one (for the current node).
- We update the diameter variable with the maximum diameter encountered during the traversal.

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
class Diameter_Of_Binary_Tree {
    private int maxDiameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        maxDiameter = 0;
        getHeight(root);
        return maxDiameter;
    }

    private int getHeight(TreeNode node) {
        if (node == null) return 0;

        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);

        maxDiameter = Math.max(maxDiameter, leftHeight + rightHeight);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static void main(String[] args) {
        Diameter_Of_Binary_Tree solver = new Diameter_Of_Binary_Tree();
        // Tree: 1 -> (2 -> (4, 5), 3)
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        System.out.println("Diameter of Binary Tree: " + solver.diameterOfBinaryTree(root)); // Expected: 3
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
