/*
QUESTION:
Given the root of a binary search tree, and an integer `k`, return the `k`th smallest value in the tree.

APPROACH:
- Perform an inorder traversal and store all node values in a list.
- Return `list.get(k - 1)`.

TIME COMPLEXITY: O(N) visiting all nodes.
SPACE COMPLEXITY: O(N) storing all values.
*/

import java.util.ArrayList;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

class Solution {
    private void inorder(TreeNode root, List<Integer> list) {
        if (root == null) return;
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }

    public int kthSmallest(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        return list.get(k - 1);
    }
}
