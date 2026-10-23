/*QUESTION:
You are visiting a farm that has a single row of fruit trees arranged from left to right. The trees are represented by an integer array fruits of size N, where fruits[i] is the type of fruit the ith tree produces.
You want to collect as much fruit as possible. However, the owner has some strict rules that you must follow:
1. You only have two baskets, and each basket can only hold a single type of fruit. There is no limit on the amount of fruit each basket can hold.
2. Starting from any tree of your choice, you must pick exactly one fruit from every tree (including the start tree) while moving to the right. The picked fruits must fit in one of the baskets.
3. Once you reach a tree with fruit that cannot fit in your baskets, you must stop.

Given the integer array fruits, return the maximum number of fruits you can pick.

Example 1:
Input: N = 3, fruits[] = {2, 1, 2}
Output: 3
Explanation: We can pick fruits from all three trees.

APPROACH:
1. Initialize an unordered_map mp to track the frequency of fruit types.
2. Initialize variables ans and start to keep track of the maximum number of fruits and the start index of the subarray.
3. Iterate through the fruit trees using a sliding window approach:
   - Increment the frequency of the current fruit type in the map.
   - Enter a while loop to adjust the start index until the number of fruit types in the map becomes more than 2.
   - During the adjustment, decrement the frequency of the fruit type at the start index and remove it from the map if the frequency becomes 0.
   - Update the ans by taking the maximum of the current ans and the length of the current subarray if the number of fruit types in the map is at most 2.
4. Return the maximum number of fruits (ans).

CODE:*/

import java.util.*;
import java.io.*;

// CODE:-
import java.util.HashMap;
import java.util.Map;

class Fruit_Into_Baskets {

    public static int totalFruit(int[] fruits) {
        Map<Integer, Integer> map = new HashMap<>();
        int left = 0;
        int maxFruits = 0;

        for (int right = 0; right < fruits.length; right++) {
            map.put(fruits[right], map.getOrDefault(fruits[right], 0) + 1);

            while (map.size() > 2) {
                map.put(fruits[left], map.get(fruits[left]) - 1);
                if (map.get(fruits[left]) == 0) {
                    map.remove(fruits[left]);
                }
                left++;
            }

            maxFruits = Math.max(maxFruits, right - left + 1);
        }

        return maxFruits;
    }

    public static void main(String[] args) {
        int[] fruits = {1, 2, 1, 2, 3};
        System.out.println("Maximum Fruits Collected: " + totalFruit(fruits));
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
