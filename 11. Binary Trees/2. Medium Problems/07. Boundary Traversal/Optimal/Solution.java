/*
QUESTION:
Given a Binary Tree, find its Boundary Traversal in anti-clockwise direction starting from root:
1. Left boundary nodes (excluding leaf nodes).
2. All leaf nodes (from left to right).
3. Right boundary nodes in reverse order (excluding leaf nodes).

APPROACH:
- Add root (if not leaf).
- Helper function to traverse left boundary: go left if available, else right, skipping leaves.
- Helper function to traverse leaves: standard inorder traversal adding leaf nodes.
- Helper function to traverse right boundary: go right if available, else left, collect nodes and reverse before adding.

TIME COMPLEXITY: O(N) visiting nodes across 3 passes.
SPACE COMPLEXITY: O(H) recursion stack.
*/

import java.util.ArrayList;

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

class Solution {
    private boolean isLeaf(TreeNode node) {
        return node.left == null && node.right == null;
    }

    private void addLeftBoundary(TreeNode root, ArrayList<Integer> res) {
        TreeNode curr = root.left;
        while (curr != null) {
            if (!isLeaf(curr)) res.add(curr.val);
            if (curr.left != null) curr = curr.left;
            else curr = curr.right;
        }
    }

    private void addLeaves(TreeNode root, ArrayList<Integer> res) {
        if (isLeaf(root)) {
            res.add(root.val);
            return;
        }
        if (root.left != null) addLeaves(root.left, res);
        if (root.right != null) addLeaves(root.right, res);
    }

    private void addRightBoundary(TreeNode root, ArrayList<Integer> res) {
        TreeNode curr = root.right;
        ArrayList<Integer> temp = new ArrayList<>();
        while (curr != null) {
            if (!isLeaf(curr)) temp.add(curr.val);
            if (curr.right != null) curr = curr.right;
            else curr = curr.left;
        }
        for (int i = temp.size() - 1; i >= 0; i--) {
            res.add(temp.get(i));
        }
    }

    public ArrayList<Integer> boundaryTraversal(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) return res;

        if (!isLeaf(root)) res.add(root.val);
        addLeftBoundary(root, res);
        addLeaves(root, res);
        addRightBoundary(root, res);

        return res;
    }
}
