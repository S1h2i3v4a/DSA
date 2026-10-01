/**
 * Problem Name: Postorder Traversal (Iterative - 1 Stack & 2 Stacks)
 * Platform: LeetCode (LC 145) / GeeksforGeeks
 * Difficulty: Medium
 * 
 * Time Complexity: O(N) where N is the number of nodes in the binary tree
 * Space Complexity: O(N) for 2-stack approach / O(H) for 1-stack approach
 * 
 * Approach:
 * 1. Two Stacks Approach:
 *    - Use stack1 for traversal and stack2 for reversing output order (Left -> Right -> Root).
 *    - Push root to stack1. While stack1 is not empty, pop node, push to stack2, and push left & right children to stack1.
 * 2. One Stack Approach:
 *    - Go left as far as possible pushing nodes.
 *    - Peek top node: if right child exists and was not just visited, move to right child.
 *    - Else pop node, visit it, and update lastVisited pointer.
 */

import java.util.*;

class Postorder_Traversal_Iterative {

    // 2-Stack Approach
    public List<Integer> postorderTwoStacks(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();

        s1.push(root);
        while (!s1.isEmpty()) {
            TreeNode curr = s1.pop();
            s2.push(curr);

            if (curr.left != null) s1.push(curr.left);
            if (curr.right != null) s1.push(curr.right);
        }

        while (!s2.isEmpty()) {
            result.add(s2.pop().val);
        }

        return result;
    }

    // 1-Stack Approach
    public List<Integer> postorderOneStack(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        TreeNode lastVisited = null;

        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                TreeNode peekNode = stack.peek();
                if (peekNode.right != null && lastVisited != peekNode.right) {
                    curr = peekNode.right;
                } else {
                    result.add(peekNode.val);
                    lastVisited = stack.pop();
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Postorder_Traversal_Iterative solver = new Postorder_Traversal_Iterative();
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);

        System.out.println("Postorder (2 Stacks): " + solver.postorderTwoStacks(root)); // [3, 2, 1]
        System.out.println("Postorder (1 Stack):  " + solver.postorderOneStack(root));  // [3, 2, 1]
    }
}
