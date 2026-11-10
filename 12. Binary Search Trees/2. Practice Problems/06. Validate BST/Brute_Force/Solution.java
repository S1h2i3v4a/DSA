/*
QUESTION:
Given the root of a binary tree, determine if it is a valid binary search tree (BST).

APPROACH:
- Compute inorder traversal into a list.
- Check if list elements are strictly increasing (`list.get(i) > list.get(i - 1)`).

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

    public boolean isValidBST(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) <= list.get(i - 1)) return false;
        }
        return true;
    }
}
