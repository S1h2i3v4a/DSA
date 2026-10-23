/*
Question:
In the universe Earth C-137, Rick discovered a special form of magnetic force between two balls if they are put in his new invented basket. Rick has n empty baskets, the ith basket is at position[i], Morty has m balls and needs to distribute the balls into the baskets such that the minimum magnetic force between any two balls is maximum.

Rick stated that magnetic force between two different balls at positions x and y is |x - y|.

Given the integer array position and the integer m. Return the required force.

Example:

Input: position = [1,2,3,4,7], m = 3
Output: 3
Explanation: Distributing the 3 balls into baskets 1, 4 and 7 will make the magnetic force between ball pairs [3, 3, 6]. The minimum magnetic force is 3. We cannot achieve a larger minimum magnetic force than 3.

Approach:
- Sort the position array in ascending order to simplify the possibility check.
- Use binary search to find the maximum minimum magnetic force.
- Set the low and high values for the binary search.
- While the low value is less than or equal to the high value, calculate the mid value.
- Check if it is possible to distribute the balls with a minimum magnetic force of mid using the isPossible() function.
- If it is possible, update the answer and set the low value to mid + 1.
- If it is not possible, set the high value to mid - 1.
- Return the answer.

Code:
*/

import java.util.*;
import java.io.*;

// CODE:-
import java.util.Arrays;

class Solution {
    public int aggressiveCows(int[] stalls, int k) {
        Arrays.sort(stalls);
        int low = 1, high = stalls[stalls.length - 1] - stalls[0];
        int ans = 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (canPlace(stalls, k, mid)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }

    private boolean canPlace(int[] stalls, int k, int dist) {
        int countCows = 1, last = stalls[0];
        for (int i = 1; i < stalls.length; i++) {
            if (stalls[i] - last >= dist) {
                countCows++;
                last = stalls[i];
            }
            if (countCows >= k) return true;
        }
        return false;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
