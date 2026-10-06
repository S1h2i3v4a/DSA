/**
 * Problem Name: Predecessor and Successor in BST
 * Platform: GeeksforGeeks
 * Difficulty: Medium
 * 
 * Time Complexity: O(H) where H is the height of the BST
 * Space Complexity: O(1) iterative constant extra space
 * 
 * Approach:
 * BST Traversal:
 * 1. Inorder Successor (smallest node strictly greater than key):
 *    - Start from root.
 *    - If curr.val > key: potential successor -> update suc = curr, move left (curr = curr.left).
 *    - Else: curr.val <= key -> move right (curr = curr.right).
 * 2. Inorder Predecessor (largest node strictly less than key):
 *    - Start from root.
 *    - If curr.val < key: potential predecessor -> update pre = curr, move right (curr = curr.right).
 *    - Else: curr.val >= key -> move left (curr = curr.left).
 */

class Predecessor_And_Successor {
    static class Res {
        TreeNode pre = null;
        TreeNode succ = null;
    }

    public static void findPreSuc(TreeNode root, Res r, int key) {
        // Find Successor
        TreeNode curr = root;
        while (curr != null) {
            if (curr.val > key) {
                r.succ = curr;
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }

        // Find Predecessor
        curr = root;
        while (curr != null) {
            if (curr.val < key) {
                r.pre = curr;
                curr = curr.right;
            } else {
                curr = curr.left;
            }
        }
    }

    public static void main(String[] args) {
        // BST: 50 -> (30 -> (20, 40), 70 -> (60, 80))
        TreeNode root = new TreeNode(50);
        root.left = new TreeNode(30);
        root.right = new TreeNode(70);
        root.left.left = new TreeNode(20);
        root.left.right = new TreeNode(40);
        root.right.left = new TreeNode(60);
        root.right.right = new TreeNode(80);

        Res r = new Res();
        int key = 65;
        findPreSuc(root, r, key);

        System.out.println("Key: " + key);
        System.out.println("Predecessor: " + (r.pre != null ? r.pre.val : "null"));   // Expected: 60
        System.out.println("Successor:   " + (r.succ != null ? r.succ.val : "null")); // Expected: 70
    }
}
