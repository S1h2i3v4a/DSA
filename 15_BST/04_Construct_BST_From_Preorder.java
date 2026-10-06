/**
 * Problem Name: Construct Binary Search Tree from Preorder Traversal
 * Platform: LeetCode (LC 1008)
 * Difficulty: Medium
 * 
 * Time Complexity: O(N) single pass through preorder array
 * Space Complexity: O(H) recursion stack space
 * 
 * Approach:
 * Upper Bound DFS:
 * Maintain an upper bound for the current subtree and an index pointer for the preorder array.
 * If the current value exceeds the upper bound, it does not belong in this subtree.
 * Otherwise:
 *   - Create TreeNode with preorder[index++].
 *   - Recurse left subtree with upperBound = node.val.
 *   - Recurse right subtree with upperBound = current upper bound.
 */

class Construct_BST_From_Preorder {
    public TreeNode bstFromPreorder(int[] preorder) {
        int[] index = {0};
        return build(preorder, Integer.MAX_VALUE, index);
    }

    private TreeNode build(int[] preorder, int upperBound, int[] index) {
        if (index[0] == preorder.length || preorder[index[0]] > upperBound) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[index[0]++]);
        root.left = build(preorder, root.val, index);
        root.right = build(preorder, upperBound, index);

        return root;
    }

    // Helper to print inorder traversal (verifying sorted BST property)
    private static void printInorder(TreeNode root) {
        if (root == null) return;
        printInorder(root.left);
        System.out.print(root.val + " ");
        printInorder(root.right);
    }

    public static void main(String[] args) {
        Construct_BST_From_Preorder solver = new Construct_BST_From_Preorder();
        int[] preorder = {8, 5, 1, 7, 10, 12};
        TreeNode root = solver.bstFromPreorder(preorder);

        System.out.print("Inorder of constructed BST: ");
        printInorder(root);
        System.out.println(); // Expected: 1 5 7 8 10 12
    }
}
