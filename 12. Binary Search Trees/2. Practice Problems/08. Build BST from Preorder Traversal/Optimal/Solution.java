/*
QUESTION:
Construct BST from preorder traversal in O(N) using upper bound constraint.

APPROACH:
- Maintain an index pointer `idx`.
- For current node, pass an `upperBound`.
- If `idx == preorder.length` or `preorder[idx] > upperBound`, return null.
- Create `root = new TreeNode(preorder[idx++])`.
- `root.left = build(preorder, root.val)`.
- `root.right = build(preorder, upperBound)`.

TIME COMPLEXITY: O(N) each element processed once.
SPACE COMPLEXITY: O(H) recursion stack.
*/

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

class Solution {
    private int idx = 0;

    private TreeNode build(int[] preorder, int bound) {
        if (idx == preorder.length || preorder[idx] > bound) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[idx++]);
        root.left = build(preorder, root.val);
        root.right = build(preorder, bound);
        return root;
    }

    public TreeNode bstFromPreorder(int[] preorder) {
        idx = 0;
        return build(preorder, Integer.MAX_VALUE);
    }
}
