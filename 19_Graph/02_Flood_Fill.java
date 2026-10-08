/**
 * Problem Name: Flood Fill
 * Platform: LeetCode (LC 733)
 * Difficulty: Easy
 * 
 * Time Complexity: O(M * N) where M is rows and N is columns
 * Space Complexity: O(M * N) recursion stack space in worst case
 * 
 * Approach:
 * Depth-First Search (DFS):
 * 1. If starting pixel already has the target color, return image immediately (prevent infinite cycle).
 * 2. Save the initial color of the starting pixel (sr, sc).
 * 3. Change color of (sr, sc) to newColor.
 * 4. Recurse 4-directionally on all neighboring pixels having the initial color.
 */

class Flood_Fill {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int initialColor = image[sr][sc];
        if (initialColor != color) {
            dfs(image, sr, sc, initialColor, color);
        }
        return image;
    }

    private void dfs(int[][] image, int r, int c, int initialColor, int newColor) {
        if (r < 0 || r >= image.length || c < 0 || c >= image[0].length || image[r][c] != initialColor) {
            return;
        }

        image[r][c] = newColor;

        dfs(image, r + 1, c, initialColor, newColor);
        dfs(image, r - 1, c, initialColor, newColor);
        dfs(image, r, c + 1, initialColor, newColor);
        dfs(image, r, c - 1, initialColor, newColor);
    }

    public static void main(String[] args) {
        Flood_Fill solver = new Flood_Fill();
        int[][] image = {
            {1, 1, 1},
            {1, 1, 0},
            {1, 0, 1}
        };
        int sr = 1, sc = 1, newColor = 2;
        int[][] res = solver.floodFill(image, sr, sc, newColor);

        System.out.println("Flood filled image:");
        for (int[] row : res) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
        // Expected:
        // 2 2 2
        // 2 2 0
        // 2 0 1
    }
}
