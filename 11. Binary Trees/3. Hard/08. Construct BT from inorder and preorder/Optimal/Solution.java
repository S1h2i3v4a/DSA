/*
QUESTION:
Given two integer arrays `preorder` and `inorder`, construct and return the binary tree.

APPROACH:
- Preorder sequence visits `Root -> Left -> Right`. Thus `preorder[preStart]` is the root.
- Find root position `inRoot` in `inorder` using a precomputed HashMap.
- Elements left of `inRoot` belong to left subtree (`numLeft = inRoot - inStart`).
- Elements right belong to right subtree.
- Recursively construct left and right subtrees.

TIME COMPLEXITY: O(N) using HashMap lookup in O(1).
SPACE COMPLEXITY: O(N) for HashMap and recursion stack.
*/

import java.util.HashMap;
import java.util.Map;

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

class Solution {
    private TreeNode build(int[] preorder, int preStart, int preEnd,
                           int[] inorder, int inStart, int inEnd,
                           Map<Integer, Integer> inMap) {
        if (preStart > preEnd || inStart > inEnd) return null;

        TreeNode root = new TreeNode(preorder[preStart]);
        int inRoot = inMap.get(root.val);
        int numsLeft = inRoot - inStart;

        root.left = build(preorder, preStart + 1, preStart + numsLeft,
                          inorder, inStart, inRoot - 1, inMap);
        root.right = build(preorder, preStart + numsLeft + 1, preEnd,
                           inorder, inRoot + 1, inEnd, inMap);
        return root;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) inMap.put(inorder[i], i);
        return build(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, inMap);
    }
}
