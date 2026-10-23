/* QUESTION:

Given the root of a binary tree, flatten the tree into a "linked list".

APPROACH:

To flatten the binary tree into a linked list, we can modify the tree in-place using a modified preorder traversal.

1. We start with the current node as the root.
2. While the current node is not NULL, we do the following:
   - If the current node has a left child, we find the rightmost node of its left subtree.
     - We make the right child of the rightmost node point to the right child of the current node.
     - We set the left child of the current node to NULL.
     - We set the right child of the current node to its left child.
   - We move to the right child of the current node.
3. We repeat this process until we have visited all the nodes in the tree.

COMPLEXITY ANALYSIS:

Let n be the number of nodes in the binary tree.
- Time Complexity: The time complexity of this approach is O(n) since we visit each node once.
- Space Complexity: The space complexity is O(1) since we don't use any extra space.

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

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
