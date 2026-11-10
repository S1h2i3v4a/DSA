/*
QUESTION:
Perform a "flood fill" on the image starting from the pixel `(sr, sc)` with `color`.

APPROACH:
- If `image[sr][sc] == color`, return image immediately (already filled).
- Record original color `initialColor = image[sr][sc]`.
- Use DFS to recolor `(sr, sc)` and its 4 adjacent neighbors with the same `initialColor`.

TIME COMPLEXITY: O(M * N).
SPACE COMPLEXITY: O(M * N) recursion stack in worst case.
*/

class Solution {
    private void dfs(int[][] image, int r, int c, int initColor, int newColor) {
        if (r < 0 || r >= image.length || c < 0 || c >= image[0].length) return;
        if (image[r][c] != initColor) return;

        image[r][c] = newColor;

        dfs(image, r + 1, c, initColor, newColor);
        dfs(image, r - 1, c, initColor, newColor);
        dfs(image, r, c + 1, initColor, newColor);
        dfs(image, r, c - 1, initColor, newColor);
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        if (image[sr][sc] == color) return image;
        dfs(image, sr, sc, image[sr][sc], color);
        return image;
    }
}
