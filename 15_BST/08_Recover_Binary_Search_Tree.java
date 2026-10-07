/**
 * Problem Name: Recover Binary Search Tree
 * Platform: LeetCode (LC 99)
 * Difficulty: Medium
 * 
 * Time Complexity: O(N) single pass inorder traversal
 * Space Complexity: O(H) recursion stack space
 * 
 * Approach:
 * Inorder Traversal Inversion Tracking:
 * In a valid BST, inorder traversal is strictly increasing.
 * If two nodes are swapped, there will be at most two places where prev.val > curr.val:
 *   1. First violation: first = prev, middle = curr.
 *   2. Second violation (if any): last = curr.
 * After traversal:
 *   - If swapped nodes were non-adjacent: swap first.val and last.val.
 *   - If swapped nodes were adjacent: swap first.val and middle.val.
 */

class Recover_Binary_Search_Tree {
    private TreeNode first = null;
    private TreeNode middle = null;
    private TreeNode last = null;
    private TreeNode prev = null;

    public void recoverTree(TreeNode root) {
        first = middle = last = prev = null;
        inorder(root);

        if (first != null && last != null) {
            int temp = first.val;
            first.val = last.val;
            last.val = temp;
        } else if (first != null && middle != null) {
            int temp = first.val;
            first.val = middle.val;
            middle.val = temp;
        }
    }

    private void inorder(TreeNode root) {
        if (root == null) return;

        inorder(root.left);

        if (prev != null && prev.val > root.val) {
            if (first == null) {
                first = prev;
                middle = root;
            } else {
                last = root;
            }
        }
        prev = root;

        inorder(root.right);
    }

    // Helper to print inorder traversal
    private static void printInorder(TreeNode root) {
        if (root == null) return;
        printInorder(root.left);
        System.out.print(root.val + " ");
        printInorder(root.right);
    }

    public static void main(String[] args) {
        Recover_Binary_Search_Tree solver = new Recover_Binary_Search_Tree();
        // Swapped BST: 1 -> (3 -> (null, 2), null) [1 and 3 are swapped]
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.left.right = new TreeNode(2);

        System.out.print("Before recovery: ");
        printInorder(root);
        System.out.println(); // 3 2 1

        solver.recoverTree(root);

        System.out.print("After recovery:  ");
        printInorder(root);
        System.out.println(); // 1 2 3
    }
}
