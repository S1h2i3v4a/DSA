/**
 * Problem Name: Flatten Binary Tree to Linked List
 * Platform: LeetCode (LC 114)
 * Difficulty: Medium
 * 
 * Time Complexity: O(N) for all three approaches
 * Space Complexity:
 *   - Approach 1 (Recursive): O(H) recursion stack space
 *   - Approach 2 (Iterative Stack): O(N) stack space
 *   - Approach 3 (Morris-like In-Place): O(1) auxiliary space
 * 
 * Approaches:
 * 1. Recursive Reverse Post-Order (Right -> Left -> Root):
 *    Traverse right subtree, then left subtree, and attach curr.right = prev, curr.left = null, prev = curr.
 * 2. Iterative using Stack:
 *    Push root to Stack. While not empty: pop curr, push right child then left child.
 *    Set curr.right = stack.peek(), curr.left = null.
 * 3. Morris-like In-Place Traversal (Optimal O(1) Space):
 *    While curr != null:
 *      If curr.left != null:
 *        Find rightmost node of curr.left: prev.
 *        Connect prev.right = curr.right.
 *        Set curr.right = curr.left, curr.left = null.
 *      Move curr = curr.right.
 */

import java.util.*;

class Flatten_Binary_Tree_To_Linked_List {

    // Approach 1: Recursive Reverse Post-Order
    private TreeNode prev = null;
    public void flattenRecursive(TreeNode root) {
        if (root == null) return;

        flattenRecursive(root.right);
        flattenRecursive(root.left);

        root.right = prev;
        root.left = null;
        prev = root;
    }

    // Approach 2: Iterative using Stack
    public void flattenStack(TreeNode root) {
        if (root == null) return;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();

            if (curr.right != null) stack.push(curr.right);
            if (curr.left != null) stack.push(curr.left);

            if (!stack.isEmpty()) {
                curr.right = stack.peek();
            }
            curr.left = null;
        }
    }

    // Approach 3: Morris-like Traversal (Optimal O(1) Space)
    public void flattenMorris(TreeNode root) {
        TreeNode curr = root;

        while (curr != null) {
            if (curr.left != null) {
                TreeNode prev = curr.left;
                while (prev.right != null) {
                    prev = prev.right;
                }
                prev.right = curr.right;
                curr.right = curr.left;
                curr.left = null;
            }
            curr = curr.right;
        }
    }

    // Default method implementing Approach 3 (Optimal)
    public void flatten(TreeNode root) {
        flattenMorris(root);
    }

    // Helper to print flattened list
    private static void printList(TreeNode root) {
        TreeNode curr = root;
        while (curr != null) {
            System.out.print(curr.val + (curr.right != null ? " -> " : ""));
            curr = curr.right;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Flatten_Binary_Tree_To_Linked_List solver = new Flatten_Binary_Tree_To_Linked_List();
        // Tree: 1 -> (2 -> (3, 4), 5 -> (null, 6))
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(6);

        solver.flatten(root);
        System.out.print("Flattened Tree: ");
        printList(root); // Expected: 1 -> 2 -> 3 -> 4 -> 5 -> 6
    }
}
