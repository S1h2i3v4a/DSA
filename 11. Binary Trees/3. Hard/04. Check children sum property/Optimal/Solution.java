/*
QUESTION:
Given a binary tree having n nodes. Check whether all of its nodes have the value equal to the sum of their child nodes.
Return 1 if all nodes satisfy children sum property, else return 0.

APPROACH:
- If node is null or leaf, return 1.
- Calculate sum of values of non-null left and right children.
- If `node.data != sum`, return 0.
- Recursively verify left and right subtrees.

TIME COMPLEXITY: O(N) visiting each node once.
SPACE COMPLEXITY: O(H) recursion stack.
*/

class Node {
    int data;
    Node left, right;
    Node(int data) { this.data = data; }
}

class Solution {
    public static int isSumProperty(Node root) {
        if (root == null || (root.left == null && root.right == null)) return 1;

        int sum = 0;
        if (root.left != null) sum += root.left.data;
        if (root.right != null) sum += root.right.data;

        if (root.data != sum) return 0;
        if (isSumProperty(root.left) == 0 || isSumProperty(root.right) == 0) return 0;

        return 1;
    }
}
