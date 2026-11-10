/*
QUESTION:
Perform Preorder, Inorder, and Postorder traversals in a single traversal using one Stack.

APPROACH:
- Store pairs `(node, state)` in stack:
  - state == 1: Preorder. Add to pre, state = 2, push left child (if any).
  - state == 2: Inorder. Add to in, state = 3, push right child (if any).
  - state == 3: Postorder. Add to post, pop from stack.

TIME COMPLEXITY: O(3 * N) = O(N) visiting each node 3 times.
SPACE COMPLEXITY: O(4 * N) for stack and result lists.
*/

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

class Pair {
    TreeNode node;
    int state;
    Pair(TreeNode node, int state) {
        this.node = node;
        this.state = state;
    }
}

class Solution {
    public static List<List<Integer>> allTraversals(TreeNode root) {
        List<Integer> pre = new ArrayList<>();
        List<Integer> in = new ArrayList<>();
        List<Integer> post = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Stack<Pair> stack = new Stack<>();
        stack.push(new Pair(root, 1));

        while (!stack.isEmpty()) {
            Pair curr = stack.pop();

            if (curr.state == 1) {
                pre.add(curr.node.val);
                curr.state = 2;
                stack.push(curr);
                if (curr.node.left != null) {
                    stack.push(new Pair(curr.node.left, 1));
                }
            } else if (curr.state == 2) {
                in.add(curr.node.val);
                curr.state = 3;
                stack.push(curr);
                if (curr.node.right != null) {
                    stack.push(new Pair(curr.node.right, 1));
                }
            } else {
                post.add(curr.node.val);
            }
        }

        result.add(in);
        result.add(pre);
        result.add(post);
        return result;
    }
}
