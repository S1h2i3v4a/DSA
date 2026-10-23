/*
Question:
Given the root of a binary search tree and an integer k, return true if there exist two elements in the BST such that their sum is equal to k, or false otherwise.

Explanation:
- We are using two stacks, one for the left traversal and one for the right traversal of the BST.
- The next() function returns the next smallest element in the BST by popping the top element from the left stack and pushing all the leftmost nodes in its right subtree to the stack.
- The before() function returns the next largest element in the BST by popping the top element from the right stack and pushing all the rightmost nodes in its left subtree to the stack.
- We use the two pointer approach with the next() and before() functions to find the pair of elements in the BST whose sum is equal to k.

Time Complexity:
- The findTarget() function uses two pointers (one for the left and one for the right traversal of the BST) and performs a two pointer traversal of the BST, taking O(n) time, where n is the number of nodes in the BST.
- The next() and before() functions take O(h) time each, where h is the height of the BST, as they traverse the leftmost and rightmost paths in the BST.

Space Complexity:
- The space complexity is O(h), where h is the height of the BST, as the stacks store the nodes in the leftmost and rightmost paths of the BST.

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
class Two_Sum_IV_BST {
    private static class BSTIterator {
        private Stack<TreeNode> stack = new Stack<>();
        private boolean isReverse; // false for next (inorder), true for before (reverse inorder)

        BSTIterator(TreeNode root, boolean isReverse) {
            this.isReverse = isReverse;
            pushAll(root);
        }

        int next() {
            TreeNode node = stack.pop();
            if (!isReverse) {
                pushAll(node.right);
            } else {
                pushAll(node.left);
            }
            return node.val;
        }

        private void pushAll(TreeNode node) {
            while (node != null) {
                stack.push(node);
                if (!isReverse) {
                    node = node.left;
                } else {
                    node = node.right;
                }
            }
        }
    }

    public boolean findTarget(TreeNode root, int k) {
        if (root == null) return false;

        BSTIterator leftIter = new BSTIterator(root, false);
        BSTIterator rightIter = new BSTIterator(root, true);

        int i = leftIter.next();
        int j = rightIter.next();

        while (i < j) {
            int sum = i + j;
            if (sum == k) {
                return true;
            } else if (sum < k) {
                i = leftIter.next();
            } else {
                j = rightIter.next();
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Two_Sum_IV_BST solver = new Two_Sum_IV_BST();
        // BST: 5 -> (3 -> (2, 4), 6 -> (null, 7))
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(7);

        System.out.println("Find target sum 9:  " + solver.findTarget(root, 9));  // true (2 + 7 or 4 + 5)
        System.out.println("Find target sum 28: " + solver.findTarget(root, 28)); // false
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
