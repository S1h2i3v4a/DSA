/*
Question:
Given a Binary Tree, find its Boundary Traversal. The traversal should be in the following order:

Left boundary nodes: defined as the path from the root to the left-most node, i.e., the leaf node you could reach when you always travel preferring the left subtree over the right subtree.
Leaf nodes: All the leaf nodes except for the ones that are part of the left or right boundary.
Reverse right boundary nodes: defined as the path from the right-most node to the root. The right-most node is the leaf node you could reach when you always travel preferring the right subtree over the left subtree. Exclude the root from this as it was already included in the traversal of left boundary nodes.
Note: If the root doesn't have a left subtree or right subtree, then the root itself is the left or right boundary.

Approach:
- We can divide the boundary traversal into three parts: left boundary nodes, leaf nodes, and reverse right boundary nodes.
- To find the left boundary nodes, we can traverse the left subtree from the root to the leftmost leaf node. We add the values of the nodes to the answer vector during this traversal.
- To find the leaf nodes, we can perform a separate recursive traversal of the binary tree and add the values of the leaf nodes (excluding the ones already included in the left boundary or right boundary) to the answer vector.
- To find the reverse right boundary nodes, we can traverse the right subtree from the root to the rightmost leaf node (excluding the root). We add the values of the nodes to the answer vector during this traversal.
- Finally, we return the answer vector containing the boundary traversal.

Complexity Analysis:
- Since we visit each node once and perform constant time operations for each node, the time complexity of this approach is O(N), where N is the number of nodes in the binary tree.
- The space complexity is O(H), where H is the height of the binary tree. In the worst case, the height of the binary tree can be N, resulting in O(N) space complexity.

Code:
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

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
