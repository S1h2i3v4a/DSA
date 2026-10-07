/**
 * Problem Name: Binary Search Tree Iterator
 * Platform: LeetCode (LC 173)
 * Difficulty: Medium
 * 
 * Time Complexity:
 *   - next(): O(1) amortized
 *   - hasNext(): O(1)
 * Space Complexity: O(H) where H is the height of the BST (stack space)
 * 
 * Approach:
 * Controlled Inorder Traversal using Stack:
 * Instead of traversing the entire BST upfront, simulate inorder traversal lazily:
 * 1. Maintain a Stack of TreeNode.
 * 2. In constructor, push all nodes along the left branch from root (pushAllLeft(root)).
 * 3. next(): Pop top node (smallest remaining element), push all left descendants of its right child
 *    (pushAllLeft(node.right)), and return node.val.
 * 4. hasNext(): Return !stack.isEmpty().
 */

import java.util.*;

class BST_Iterator {
    private Stack<TreeNode> stack;

    public BST_Iterator(TreeNode root) {
        stack = new Stack<>();
        pushAllLeft(root);
    }

    public int next() {
        TreeNode node = stack.pop();
        pushAllLeft(node.right);
        return node.val;
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }

    private void pushAllLeft(TreeNode node) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }

    public static void main(String[] args) {
        // BST: 7 -> (3, 15 -> (9, 20))
        TreeNode root = new TreeNode(7);
        root.left = new TreeNode(3);
        root.right = new TreeNode(15);
        root.right.left = new TreeNode(9);
        root.right.right = new TreeNode(20);

        BST_Iterator iterator = new BST_Iterator(root);
        System.out.println("next(): " + iterator.next());       // Expected: 3
        System.out.println("next(): " + iterator.next());       // Expected: 7
        System.out.println("hasNext(): " + iterator.hasNext()); // Expected: true
        System.out.println("next(): " + iterator.next());       // Expected: 9
        System.out.println("hasNext(): " + iterator.hasNext()); // Expected: true
        System.out.println("next(): " + iterator.next());       // Expected: 15
        System.out.println("hasNext(): " + iterator.hasNext()); // Expected: true
        System.out.println("next(): " + iterator.next());       // Expected: 20
        System.out.println("hasNext(): " + iterator.hasNext()); // Expected: false
    }
}
