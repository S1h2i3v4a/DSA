/*
QUESTION:
Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer.
Design an algorithm to serialize and deserialize a binary tree.

APPROACH:
- Serialize: Level-order BFS traversal, appending node values separated by commas. Append "#" for null nodes.
- Deserialize: Split string by commas, use queue to reconstruct tree level-by-level linking left and right children.

TIME COMPLEXITY: O(N) serialization and deserialization.
SPACE COMPLEXITY: O(N) for string representation and queue.
*/

import java.util.LinkedList;
import java.util.Queue;

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

class Solution {
    public String serialize(TreeNode root) {
        if (root == null) return "";
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (curr == null) {
                sb.append("#,");
            } else {
                sb.append(curr.val).append(",");
                queue.offer(curr.left);
                queue.offer(curr.right);
            }
        }
        return sb.toString();
    }

    public TreeNode deserialize(String data) {
        if (data == null || data.isEmpty()) return null;
        String[] values = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(values[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int i = 1;
        while (!queue.isEmpty() && i < values.length) {
            TreeNode parent = queue.poll();

            if (!values[i].equals("#")) {
                TreeNode left = new TreeNode(Integer.parseInt(values[i]));
                parent.left = left;
                queue.offer(left);
            }
            i++;

            if (i < values.length && !values[i].equals("#")) {
                TreeNode right = new TreeNode(Integer.parseInt(values[i]));
                parent.right = right;
                queue.offer(right);
            }
            i++;
        }
        return root;
    }
}
