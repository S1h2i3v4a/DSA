/*
QUESTION:
Given the root of a binary tree, return the maximum width of the given tree.
The maximum width of a tree is the maximum width among all levels.
The width of one level is defined as the length between the end-nodes (the leftmost and rightmost non-null nodes),
where the null nodes between the end-nodes that would be present in a complete binary tree extending down to that level are also counted into the length calculation.

APPROACH:
- Use BFS with positional indices:
  - If node has index `i`: left child is `2*i + 1`, right child is `2*i + 2`.
  - To prevent integer overflow, subtract `minLevelIndex` from all indices at each level.
  - Level width is `lastIndex - firstIndex + 1`.

TIME COMPLEXITY: O(N) visiting each node once.
SPACE COMPLEXITY: O(N) for BFS queue.
*/

import java.util.LinkedList;
import java.util.Queue;

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

class Pair {
    TreeNode node;
    long idx;
    Pair(TreeNode node, long idx) {
        this.node = node;
        this.idx = idx;
    }
}

class Solution {
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        int maxWidth = 0;
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(root, 0));

        while (!queue.isEmpty()) {
            int size = queue.size();
            long minIdx = queue.peek().idx;
            long first = 0, last = 0;

            for (int i = 0; i < size; i++) {
                Pair curr = queue.poll();
                long curIdx = curr.idx - minIdx;
                if (i == 0) first = curIdx;
                if (i == size - 1) last = curIdx;

                if (curr.node.left != null) {
                    queue.offer(new Pair(curr.node.left, 2 * curIdx + 1));
                }
                if (curr.node.right != null) {
                    queue.offer(new Pair(curr.node.right, 2 * curIdx + 2));
                }
            }
            maxWidth = Math.max(maxWidth, (int) (last - first + 1));
        }
        return maxWidth;
    }
}
