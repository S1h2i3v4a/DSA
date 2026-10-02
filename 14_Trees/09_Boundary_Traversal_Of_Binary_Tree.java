/**
 * Problem Name: Boundary Traversal of Binary Tree
 * Platform: GeeksforGeeks
 * Difficulty: Medium
 * 
 * Time Complexity: O(N) where N is total number of nodes
 * Space Complexity: O(H) stack/recursion space for boundary and leaf collections
 * 
 * Approach:
 * Anti-Clockwise Boundary Traversal in 4 steps:
 * 1. Add root value (if not a leaf node).
 * 2. Add Left Boundary (excluding leaf nodes): move left if available, else right.
 * 3. Add Leaf Nodes (pre-order/in-order scan): add all nodes where left == null && right == null.
 * 4. Add Right Boundary (excluding leaf nodes): move right if available, else left. Store in stack to reverse order for anti-clockwise traversal.
 */

import java.util.*;

class Boundary_Traversal_Of_Binary_Tree {

    public ArrayList<Integer> boundaryTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) return result;

        if (!isLeaf(root)) {
            result.add(root.val);
        }

        addLeftBoundary(root.left, result);
        addLeaves(root, result);
        addRightBoundary(root.right, result);

        return result;
    }

    private boolean isLeaf(TreeNode node) {
        return node != null && node.left == null && node.right == null;
    }

    private void addLeftBoundary(TreeNode node, ArrayList<Integer> result) {
        TreeNode curr = node;
        while (curr != null) {
            if (!isLeaf(curr)) {
                result.add(curr.val);
            }
            if (curr.left != null) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
    }

    private void addLeaves(TreeNode node, ArrayList<Integer> result) {
        if (node == null) return;

        if (isLeaf(node)) {
            result.add(node.val);
            return;
        }

        addLeaves(node.left, result);
        addLeaves(node.right, result);
    }

    private void addRightBoundary(TreeNode node, ArrayList<Integer> result) {
        TreeNode curr = node;
        Stack<Integer> tempStack = new Stack<>();

        while (curr != null) {
            if (!isLeaf(curr)) {
                tempStack.push(curr.val);
            }
            if (curr.right != null) {
                curr = curr.right;
            } else {
                curr = curr.left;
            }
        }

        while (!tempStack.isEmpty()) {
            result.add(tempStack.pop());
        }
    }

    public static void main(String[] args) {
        Boundary_Traversal_Of_Binary_Tree solver = new Boundary_Traversal_Of_Binary_Tree();
        // Tree: 1 -> (2 -> (4, 5 -> (8, 9)), 3 -> (6, 7))
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.left.right.left = new TreeNode(8);
        root.left.right.right = new TreeNode(9);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        System.out.println("Boundary Traversal: " + solver.boundaryTraversal(root));
        // Expected: [1, 2, 4, 8, 9, 6, 7, 3]
    }
}
