/**
 QUESTION:
 Given the root of a binary tree, check whether it is a mirror of itself
 (i.e., symmetric around its center).
 
 Example:
 Input: root = [1,2,2,3,4,4,3]
 Output: true
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
class Symmetric_Tree {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }

    private boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        if (t1.val != t2.val) return false;

        return isMirror(t1.left, t2.right) && isMirror(t1.right, t2.left);
    }

    public static void main(String[] args) {
        Symmetric_Tree solver = new Symmetric_Tree();
        // Symmetric Tree: 1 -> (2 -> (3, 4), 2 -> (4, 3))
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(2);
        root1.left.left = new TreeNode(3);
        root1.left.right = new TreeNode(4);
        root1.right.left = new TreeNode(4);
        root1.right.right = new TreeNode(3);

        System.out.println("Is root1 symmetric: " + solver.isSymmetric(root1)); // true

        // Asymmetric Tree: 1 -> (2 -> (null, 3), 2 -> (null, 3))
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(2);
        root2.left.right = new TreeNode(3);
        root2.right.right = new TreeNode(3);

        System.out.println("Is root2 symmetric: " + solver.isSymmetric(root2)); // false
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
