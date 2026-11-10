/*
QUESTION:
You are given the root node of a binary search tree (BST) and a value to insert into the tree.
Return the root node of the BST after the insertion.

APPROACH:
- If root is null, return `new TreeNode(val)`.
- Traverse tree iteratively:
  - If `val < curr.val`:
    - If `curr.left != null`, `curr = curr.left`.
    - Else `curr.left = new TreeNode(val)`, break.
  - Else:
    - If `curr.right != null`, `curr = curr.right`.
    - Else `curr.right = new TreeNode(val)`, break.

TIME COMPLEXITY: O(H) where H is tree height.
SPACE COMPLEXITY: O(1) iterative insertion.
*/

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        TreeNode curr = root;

        while (true) {
            if (val < curr.val) {
                if (curr.left != null) {
                    curr = curr.left;
                } else {
                    curr.left = new TreeNode(val);
                    break;
                }
            } else {
                if (curr.right != null) {
                    curr = curr.right;
                } else {
                    curr.right = new TreeNode(val);
                    break;
                }
            }
        }
        return root;
    }
}
