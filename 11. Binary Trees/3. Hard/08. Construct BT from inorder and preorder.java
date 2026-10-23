/* QUESTION:

Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree and inorder is the inorder traversal of the same tree, construct and return the binary tree.

APPROACH:

The preorder traversal follows the root-left-right order, while the inorder traversal follows the left-root-right order. Based on these two traversals, we can construct the binary tree recursively.

1. The first element in the preorder traversal array is the root of the binary tree.
2. We can find the position of the root in the inorder traversal array. All the elements to the left of this position will be in the left subtree, and all the elements to the right will be in the right subtree.
3. Recursively, we can build the left and right subtrees using the corresponding portions of the preorder and inorder traversal arrays.
4. The recursive function takes the index of the current element in the preorder traversal array, the starting and ending indices of the current portion in the inorder traversal array, and the preorder and inorder traversal arrays as inputs.
5. In each recursive call, we create a new node with the value of the current element in the preorder traversal array and determine its left and right subtrees by calling the recursive function on the corresponding portions of the inorder and preorder traversal arrays.
6. The base case is when the starting index is greater than the ending index, indicating an empty portion of the tree. In this case, we return NULL.
7. Finally, we return the root of the constructed binary tree.

COMPLEXITY ANALYSIS:

Let n be the number of nodes in the binary tree.
- Time Complexity: The time complexity of this approach is O(n), as we visit each node once.
- Space Complexity: The space complexity is O(n) for the recursive call stack.

CODE:
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

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
