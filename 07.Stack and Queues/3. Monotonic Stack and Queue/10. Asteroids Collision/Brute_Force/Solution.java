/*
QUESTION:
We are given an array `asteroids` of integers representing asteroids in a row.
Find out the state of the asteroids after all collisions.

APPROACH:
- Simulate collisions iteratively using an `ArrayList`.
- Look for adjacent elements where `list.get(i) > 0` and `list.get(i + 1) < 0`.
- Resolve the collision: remove the smaller asteroid (or both if equal).
- Repeat until no colliding pair exists.

TIME COMPLEXITY: O(N^2) worst-case simulation with repeated scans.
SPACE COMPLEXITY: O(N) for list operations.
*/

import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        List<Integer> list = new ArrayList<>();
        for (int a : asteroids) list.add(a);

        boolean collisionOccurred = true;
        while (collisionOccurred) {
            collisionOccurred = false;
            for (int i = 0; i < list.size() - 1; i++) {
                if (list.get(i) > 0 && list.get(i + 1) < 0) {
                    int left = list.get(i);
                    int right = -list.get(i + 1);

                    if (left > right) {
                        list.remove(i + 1);
                    } else if (left < right) {
                        list.remove(i);
                    } else {
                        list.remove(i + 1);
                        list.remove(i);
                    }
                    collisionOccurred = true;
                    break;
                }
            }
        }

        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) result[i] = list.get(i);
        return result;
    }
}
