/**
 * Problem Name: Construct Binary Tree from Preorder and Inorder Traversal
 * Platform: LeetCode (LC 105)
 * Difficulty: Medium
 * 
 * Time Complexity: O(N) where N is the number of nodes
 * Space Complexity: O(N) for HashMap lookup and recursion stack
 * 
 * Approach:
 * Divide and Conquer with Hash Map:
 * 1. Build a HashMap storing inorder[val] -> index to find root position in O(1).
 * 2. In preorder array, root is always at preStart.
 * 3. Find root index in inorder array: inRoot.
 * 4. Number of nodes in left subtree = inRoot - inStart.
 * 5. Recurse left subtree:
 *    - preorder range: [preStart + 1, preStart + numsLeft]
 *    - inorder range:  [inStart, inRoot - 1]
 * 6. Recurse right subtree:
 *    - preorder range: [preStart + numsLeft + 1, preEnd]
 *    - inorder range:  [inRoot + 1, inEnd]
 */

import java.util.*;

class Construct_Binary_Tree_From_Preorder_And_Inorder {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }

        return build(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, inMap);
    }

    private TreeNode build(int[] preorder, int preStart, int preEnd,
                           int[] inorder, int inStart, int inEnd,
                           Map<Integer, Integer> inMap) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[preStart]);
        int inRoot = inMap.get(root.val);
        int numsLeft = inRoot - inStart;

        root.left = build(preorder, preStart + 1, preStart + numsLeft,
                          inorder, inStart, inRoot - 1, inMap);

        root.right = build(preorder, preStart + numsLeft + 1, preEnd,
                           inorder, inRoot + 1, inEnd, inMap);

        return root;
    }

    // Helper method to print inorder traversal of reconstructed tree
    private static void printInorder(TreeNode root) {
        if (root == null) return;
        printInorder(root.left);
        System.out.print(root.val + " ");
        printInorder(root.right);
    }

    public static void main(String[] args) {
        Construct_Binary_Tree_From_Preorder_And_Inorder solver = new Construct_Binary_Tree_From_Preorder_And_Inorder();
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};

        TreeNode root = solver.buildTree(preorder, inorder);
        System.out.print("Reconstructed Tree Inorder: ");
        printInorder(root);
        System.out.println(); // Expected: 9 3 15 20 7
    }
}
