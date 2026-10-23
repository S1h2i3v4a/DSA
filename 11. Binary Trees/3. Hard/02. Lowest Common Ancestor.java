/* QUESTION:

Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: "The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself)."

Example 1:
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.


APPROACH:

To find the lowest common ancestor (LCA) of two nodes `p` and `q` in a binary tree, we can use a recursive approach. We start from the root of the tree and check if either `p` or `q` matches the current node. If one of them matches, it means that the current node is the LCA. Otherwise, we recursively search for `p` and `q` in the left and right subtrees.

COMPLEXITY ANALYSIS:

Let `n` be the number of nodes in the binary tree.
- Time Complexity: The time complexity of this approach is O(n) since we may have to visit all nodes of the binary tree in the worst case.
- Space Complexity: The space complexity is O(h) for the recursive call stack, where `h` is the height of the binary tree. In the worst case, when the binary tree is skewed, the space complexity becomes O(n).


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
class Lowest_Common_Ancestor {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) {
            return root;
        }

        return left != null ? left : right;
    }

    public static void main(String[] args) {
        Lowest_Common_Ancestor solver = new Lowest_Common_Ancestor();
        // Tree: 3 -> (5 -> (6, 2 -> (7, 4)), 1 -> (0, 8))
        TreeNode root = new TreeNode(3);
        TreeNode node5 = new TreeNode(5);
        TreeNode node1 = new TreeNode(1);
        TreeNode node6 = new TreeNode(6);
        TreeNode node2 = new TreeNode(2);
        TreeNode node0 = new TreeNode(0);
        TreeNode node8 = new TreeNode(8);
        TreeNode node7 = new TreeNode(7);
        TreeNode node4 = new TreeNode(4);

        root.left = node5;
        root.right = node1;
        node5.left = node6;
        node5.right = node2;
        node1.left = node0;
        node1.right = node8;
        node2.left = node7;
        node2.right = node4;

        TreeNode lca = solver.lowestCommonAncestor(root, node5, node1);
        System.out.println("LCA of 5 and 1: " + (lca != null ? lca.val : "null")); // Expected: 3

        TreeNode lca2 = solver.lowestCommonAncestor(root, node5, node4);
        System.out.println("LCA of 5 and 4: " + (lca2 != null ? lca2.val : "null")); // Expected: 5
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
