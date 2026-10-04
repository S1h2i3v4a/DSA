/**
 * Problem Name: Unique Binary Tree Possibility (Requirements)
 * Platform: GeeksforGeeks
 * Difficulty: Easy
 * 
 * Time Complexity: O(1)
 * Space Complexity: O(1)
 * 
 * Traversal Code Mapping:
 *   1 -> Preorder Traversal
 *   2 -> Inorder Traversal
 *   3 -> Postorder Traversal
 * 
 * Theory & Approach:
 * To construct a UNIQUE Binary Tree, we MUST have the Inorder traversal because:
 *   - Preorder/Postorder tells us the root of the tree/subtree.
 *   - Inorder tells us which elements belong to the left subtree and which belong to the right subtree.
 * Without Inorder, we cannot uniquely partition left vs right children (e.g., Preorder + Postorder
 * only creates a unique tree if the tree is full binary tree, but NOT in general).
 * 
 * Therefore, a unique binary tree is possible if and only if:
 *   - Exactly one of the two given traversals is Inorder (2).
 *   - The two given traversals are distinct (a != b).
 */

class Unique_Binary_Tree_Possibility {
    public static boolean isPossible(int a, int b) {
        // Return true if one of the traversals is Inorder (2) and both are distinct
        if (a == b) return false;
        return (a == 2 || b == 2);
    }

    public static void main(String[] args) {
        System.out.println("Preorder (1) & Inorder (2):   " + isPossible(1, 2)); // true
        System.out.println("Inorder (2) & Postorder (3):  " + isPossible(2, 3)); // true
        System.out.println("Preorder (1) & Postorder (3): " + isPossible(1, 3)); // false
        System.out.println("Inorder (2) & Inorder (2):    " + isPossible(2, 2)); // false
    }
}
