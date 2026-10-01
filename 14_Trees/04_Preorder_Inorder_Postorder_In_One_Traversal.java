/**
 * Problem Name: Preorder, Inorder, and Postorder in One Traversal
 * Platform: GeeksforGeeks / Striver SDE Sheet
 * Difficulty: Medium
 * 
 * Time Complexity: O(N) where N is the total number of nodes in the binary tree
 * Space Complexity: O(N) for Stack and result lists
 * 
 * Approach:
 * Single Pass using Stack of Pair<TreeNode, Integer state>:
 * - State 1: Preorder -> add to preorder list, increment state to 2, push node.left if not null.
 * - State 2: Inorder -> add to inorder list, increment state to 3, push node.right if not null.
 * - State 3: Postorder -> add to postorder list, pop pair from stack.
 */

import java.util.*;

class Preorder_Inorder_Postorder_In_One_Traversal {
    private static class Pair {
        TreeNode node;
        int state;

        Pair(TreeNode node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    public static void allTraversals(TreeNode root) {
        List<Integer> preorder = new ArrayList<>();
        List<Integer> inorder = new ArrayList<>();
        List<Integer> postorder = new ArrayList<>();

        if (root == null) return;

        Stack<Pair> stack = new Stack<>();
        stack.push(new Pair(root, 1));

        while (!stack.isEmpty()) {
            Pair p = stack.peek();

            if (p.state == 1) {
                preorder.add(p.node.val);
                p.state++;
                if (p.node.left != null) {
                    stack.push(new Pair(p.node.left, 1));
                }
            } else if (p.state == 2) {
                inorder.add(p.node.val);
                p.state++;
                if (p.node.right != null) {
                    stack.push(new Pair(p.node.right, 1));
                }
            } else {
                postorder.add(p.node.val);
                stack.pop();
            }
        }

        System.out.println("Preorder:  " + preorder);
        System.out.println("Inorder:   " + inorder);
        System.out.println("Postorder: " + postorder);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        allTraversals(root);
        // Preorder:  [1, 2, 4, 5, 3]
        // Inorder:   [4, 2, 5, 1, 3]
        // Postorder: [4, 5, 2, 3, 1]
    }
}
