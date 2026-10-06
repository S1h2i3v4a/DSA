/**
 * Problem Name: Kth Smallest Element in a BST
 * Platform: LeetCode (LC 230)
 * Difficulty: Medium
 * 
 * Time Complexity: O(H + K) where H is the height of the BST
 * Space Complexity: O(H) recursion/stack space
 * 
 * Approach:
 * Inorder Traversal Property:
 * An inorder traversal of a BST visits nodes in strictly increasing sorted order.
 * Perform iterative inorder traversal using a Stack and keep a counter.
 * Pop elements one by one; when count == k, the current node is the K-th smallest.
 */

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

class Kth_Smallest_Element_In_BST {
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        int count = 0;

        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            curr = stack.pop();
            count++;
            if (count == k) {
                return curr.val;
            }

            curr = curr.right;
        }

        return -1;
    }

    public static void main(String[] args) {
        Kth_Smallest_Element_In_BST solver = new Kth_Smallest_Element_In_BST();
        // BST: 3 -> (1 -> (null, 2), 4)
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.left.right = new TreeNode(2);

        System.out.println("1st smallest element: " + solver.kthSmallest(root, 1)); // Expected: 1
        System.out.println("3rd smallest element: " + solver.kthSmallest(root, 3)); // Expected: 3
    }
}
