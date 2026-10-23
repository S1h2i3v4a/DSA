/*
Question:
You are given the root of a binary search tree (BST), where the values of exactly two nodes of the tree were swapped by mistake. Recover the tree without changing its structure.

Explanation:
- We perform an in-order traversal of the BST to find the two nodes that are swapped.
- During the in-order traversal, we keep track of the previous node and compare it with the current node. If the current node is less than the previous node, then we have found the two swapped nodes.
- We swap the values of the two nodes to recover the BST.

Time Complexity:
- The in-order traversal takes O(n) time, where n is the number of nodes in the BST.

Space Complexity:
- The space complexity is O(h), where h is the height of the BST, as the recursion stack stores the nodes in the leftmost path of the BST.

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
class Recover_Binary_Search_Tree {
    private TreeNode first = null;
    private TreeNode middle = null;
    private TreeNode last = null;
    private TreeNode prev = null;

    public void recoverTree(TreeNode root) {
        first = middle = last = prev = null;
        inorder(root);

        if (first != null && last != null) {
            int temp = first.val;
            first.val = last.val;
            last.val = temp;
        } else if (first != null && middle != null) {
            int temp = first.val;
            first.val = middle.val;
            middle.val = temp;
        }
    }

    private void inorder(TreeNode root) {
        if (root == null) return;

        inorder(root.left);

        if (prev != null && prev.val > root.val) {
            if (first == null) {
                first = prev;
                middle = root;
            } else {
                last = root;
            }
        }
        prev = root;

        inorder(root.right);
    }

    // Helper to print inorder traversal
    private static void printInorder(TreeNode root) {
        if (root == null) return;
        printInorder(root.left);
        System.out.print(root.val + " ");
        printInorder(root.right);
    }

    public static void main(String[] args) {
        Recover_Binary_Search_Tree solver = new Recover_Binary_Search_Tree();
        // Swapped BST: 1 -> (3 -> (null, 2), null) [1 and 3 are swapped]
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.left.right = new TreeNode(2);

        System.out.print("Before recovery: ");
        printInorder(root);
        System.out.println(); // 3 2 1

        solver.recoverTree(root);

        System.out.print("After recovery:  ");
        printInorder(root);
        System.out.println(); // 1 2 3
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
