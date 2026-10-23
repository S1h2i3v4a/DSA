/*
Question:
Given a binary search tree (BST), find the lowest common ancestor (LCA) node of two given nodes in the BST.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”

Example:
Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
Output: 6
Explanation: The LCA of nodes 2 and 8 is 6.

Approach:
- Traverse the BST from the root.
- If both the nodes p and q are smaller than the current node's value, then the LCA must be in the left subtree. So, recursively call the function on the left subtree.
- If both the nodes p and q are greater than the current node's value, then the LCA must be in the right subtree. So, recursively call the function on the right subtree.
- If the current node's value is between the values of nodes p and q, then the current node is the LCA.

Time Complexity: O(h)
- h is the height of the BST.
- In the worst case, the height of the BST can be n for a skewed tree, so the time complexity becomes O(n).
- However, for a balanced BST, the height is log n, so the time complexity becomes O(log n).

Space Complexity: O(h)
- The space complexity is determined by the recursion stack during the function call.
- In the worst case, the height of the BST can be n for a skewed tree, so the space complexity becomes O(n).
- However, for a balanced BST, the height is log n, so the space complexity becomes O(log n).
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
class Lowest_Common_Ancestor_In_BST {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode curr = root;

        while (curr != null) {
            if (p.val < curr.val && q.val < curr.val) {
                curr = curr.left;
            } else if (p.val > curr.val && q.val > curr.val) {
                curr = curr.right;
            } else {
                return curr; // Split point or matching node
            }
        }

        return null;
    }

    public static void main(String[] args) {
        Lowest_Common_Ancestor_In_BST solver = new Lowest_Common_Ancestor_In_BST();
        // BST: 6 -> (2 -> (0, 4 -> (3, 5)), 8 -> (7, 9))
        TreeNode root = new TreeNode(6);
        TreeNode node2 = new TreeNode(2);
        TreeNode node8 = new TreeNode(8);
        TreeNode node0 = new TreeNode(0);
        TreeNode node4 = new TreeNode(4);
        TreeNode node7 = new TreeNode(7);
        TreeNode node9 = new TreeNode(9);
        TreeNode node3 = new TreeNode(3);
        TreeNode node5 = new TreeNode(5);

        root.left = node2;
        root.right = node8;
        node2.left = node0;
        node2.right = node4;
        node4.left = node3;
        node4.right = node5;
        node8.left = node7;
        node8.right = node9;

        TreeNode lca1 = solver.lowestCommonAncestor(root, node2, node8);
        System.out.println("LCA of 2 and 8: " + (lca1 != null ? lca1.val : "null")); // Expected: 6

        TreeNode lca2 = solver.lowestCommonAncestor(root, node2, node4);
        System.out.println("LCA of 2 and 4: " + (lca2 != null ? lca2.val : "null")); // Expected: 2
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
