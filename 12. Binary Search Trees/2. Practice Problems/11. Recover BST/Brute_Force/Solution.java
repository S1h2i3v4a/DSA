/*
QUESTION:
You are given the root of a binary search tree (BST), where the values of exactly two nodes of the tree were swapped by mistake.
Recover the tree without changing its structure.

APPROACH:
- Collect inorder traversal node values into a list.
- Sort the list.
- Perform a second inorder traversal, overwriting node values with the sorted values.

TIME COMPLEXITY: O(N log N) for sorting.
SPACE COMPLEXITY: O(N) to store values.
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

class Solution {
    private int idx = 0;

    private void getValues(TreeNode root, List<Integer> list) {
        if (root == null) return;
        getValues(root.left, list);
        list.add(root.val);
        getValues(root.right, list);
    }

    private void setValues(TreeNode root, List<Integer> list) {
        if (root == null) return;
        setValues(root.left, list);
        root.val = list.get(idx++);
        setValues(root.right, list);
    }

    public void recoverTree(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        getValues(root, list);
        Collections.sort(list);
        idx = 0;
        setValues(root, list);
    }
}
