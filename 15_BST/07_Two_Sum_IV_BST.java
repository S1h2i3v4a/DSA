/**
 * Problem Name: Two Sum IV - Input is a BST
 * Platform: LeetCode (LC 653)
 * Difficulty: Easy / Medium
 * 
 * Time Complexity: O(N) where N is the number of nodes in the BST
 * Space Complexity: O(H) for two iterator stacks
 * 
 * Approach:
 * Two BST Iterators (Next & Before):
 * Simulate the two-pointer technique on a sorted array without storing all elements in an array:
 * 1. BSTIterator(root, isReverse = false) provides next() in ascending order.
 * 2. BSTIterator(root, isReverse = true) provides before() in descending order.
 * 3. Initialize i = next(), j = before().
 * 4. While i < j:
 *    - If i + j == k: return true.
 *    - If i + j < k: i = next().
 *    - If i + j > k: j = before().
 */

import java.util.*;

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
