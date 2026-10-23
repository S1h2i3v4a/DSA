/*
QUESTION:

An image is represented by an m x n integer grid image where image[i][j] represents the pixel value of the image.

You are also given three integers sr, sc, and color. You should perform a flood fill on the image starting from the pixel image[sr][sc].

To perform a flood fill, consider the starting pixel, plus any pixels connected 4-directionally to the starting pixel of the same color as the starting pixel, plus any pixels connected 4-directionally to those pixels (also with the same color), and so on. Replace the color of all of the aforementioned pixels with color.

Return the modified image after performing the flood fill.

Example:

Input: image = [[1,1,1],[1,1,0],[1,0,1]], sr = 1, sc = 1, color = 2
Output: [[2,2,2],[2,2,0],[2,0,1]]
Explanation: From the center of the image with position (sr, sc) = (1, 1) (i.e., the red pixel), all pixels connected by a path of the same color as the starting pixel (i.e., the blue pixels) are colored with the new color.
Note the bottom corner is not colored 2, because it is not 4-directionally connected to the starting pixel.

CODE:
*/

import java.util.*;
import java.io.*;

// CODE:-
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

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
