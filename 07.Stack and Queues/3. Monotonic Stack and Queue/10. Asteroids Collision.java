/*
QUESTION:
We are given an array of integers representing asteroids in a row. Each asteroid moves at the same speed.
If two asteroids meet, the smaller one will explode. If both are the same size, both will explode.
Find out the state of the asteroids after all collisions.

APPROACH:
To solve this problem, we can use a stack to simulate the asteroid collisions.
1. We iterate through each asteroid in the given array.
2. For each asteroid, we check if it will collide with the asteroids in the stack.
   - If the current asteroid is moving to the right (positive direction), we simply push it onto the stack.
   - If the current asteroid is moving to the left (negative direction), we compare its size with the asteroids in the stack.
     - If the stack is empty or the top asteroid in the stack is also moving to the left, we push the current asteroid onto the stack.
     - If the top asteroid in the stack is moving to the right and its size is smaller than the current asteroid, we pop the top asteroid from the stack and continue the comparison.
     - If the top asteroid in the stack is moving to the right and its size is equal to or greater than the current asteroid, we do not push the current asteroid onto the stack and continue to the next asteroid.
3. After processing all the asteroids, the remaining asteroids in the stack represent the final state after collisions.

Example:
Input: asteroids = [5,10,-5]
Output: [5,10]
Explanation: The 10 and -5 collide resulting in 10. The 5 and 10 never collide.

CODE:
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> st = new Stack<>();
        for (int a : asteroids) {
            if (a > 0) {
                st.push(a);
            } else {
                while (!st.isEmpty() && st.peek() > 0 && st.peek() < -a) {
                    st.pop();
                }
                if (!st.isEmpty() && st.peek() == -a) {
                    st.pop();
                } else if (st.isEmpty() || st.peek() < 0) {
                    st.push(a);
                }
            }
        }
        int[] ans = new int[st.size()];
        for (int i = ans.length - 1; i >= 0; i--) {
            ans[i] = st.pop();
        }
        return ans;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
