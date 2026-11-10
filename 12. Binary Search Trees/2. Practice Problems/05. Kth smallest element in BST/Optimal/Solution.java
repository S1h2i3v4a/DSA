/*
QUESTION:
Find `k`th smallest element in BST in O(H + K) time and O(H) space.

APPROACH:
- Inorder traversal visits nodes in strictly increasing order.
- Maintain a counter `count = 0`.
- Traverse left subtree.
- Visit root: increment `count`. If `count == k`, record answer and return.
- Traverse right subtree.

TIME COMPLEXITY: O(H + K) early exit as soon as Kth element is reached.
SPACE COMPLEXITY: O(H) recursion stack.
*/

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

class Solution {
    private int count = 0;
    private int result = -1;

    private void inorder(TreeNode root, int k) {
        if (root == null || count >= k) return;

        inorder(root.left, k);

        count++;
        if (count == k) {
            result = root.val;
            return;
        }

        inorder(root.right, k);
    }

    public int kthSmallest(TreeNode root, int k) {
        count = 0;
        result = -1;
        inorder(root, k);
        return result;
    }
}
