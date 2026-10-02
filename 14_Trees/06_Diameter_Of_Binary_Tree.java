/**
 * Problem Name: Diameter of Binary Tree
 * Platform: LeetCode (LC 543)
 * Difficulty: Easy
 * 
 * Time Complexity: O(N) where N is the total number of nodes
 * Space Complexity: O(H) recursion stack space (H is height of tree)
 * 
 * Approach:
 * Bottom-Up DFS:
 * For every node, the maximum diameter passing through it is:
 * diameterAtNode = height(leftSubtree) + height(rightSubtree).
 * Maintain a global maximum diameter variable updated at each node during height calculation.
 */

class Diameter_Of_Binary_Tree {
    private int maxDiameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        maxDiameter = 0;
        getHeight(root);
        return maxDiameter;
    }

    private int getHeight(TreeNode node) {
        if (node == null) return 0;

        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);

        maxDiameter = Math.max(maxDiameter, leftHeight + rightHeight);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static void main(String[] args) {
        Diameter_Of_Binary_Tree solver = new Diameter_Of_Binary_Tree();
        // Tree: 1 -> (2 -> (4, 5), 3)
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        System.out.println("Diameter of Binary Tree: " + solver.diameterOfBinaryTree(root)); // Expected: 3
    }
}
