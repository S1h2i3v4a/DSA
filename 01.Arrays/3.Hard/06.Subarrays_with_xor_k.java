/*QUESTION:
Given an array 'A' consisting of 'N' integers and an integer 'B', find the number of subarrays of array 'A' whose bitwise XOR of all elements is equal to 'B'.

Example:
Input: 'N' = 4, 'B' = 2
'A' = [1, 2, 3, 2]
Output: 3
Explanation: Subarrays have bitwise xor equal to '2' are: [1, 2, 3, 2], [2], [2].

APPROACH:
To find the number of subarrays with bitwise XOR equal to B, we can use the technique of prefix XOR along with a hashmap.
1. Initialize a variable `prefixXOR` to keep track of the prefix XOR while iterating through the array.
2. Initialize a variable `count` to keep track of the count of subarrays with XOR equal to B.
3. Initialize a hashmap `xorCount` to store the frequency of prefix XOR values encountered so far.
4. Set the initial prefix XOR to 0 and set its count to 1 in the `xorCount` hashmap.
5. Iterate through the array and update the prefix XOR by XOR-ing each element.
6. Check if the current prefix XOR is equal to B. If it is, increment the `count` variable.
7. Check if the XOR of the current prefix XOR with B exists in the `xorCount` hashmap. If it does, add the count of that XOR value to the `count` variable.
8. Increment the count of the current prefix XOR in the `xorCount` hashmap.
9. Finally, return the `count` variable as the number of subarrays with XOR equal to B.

CODE:
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int solve(ArrayList<Integer> A, int B) {
        int xr = 0, cnt = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 0; i < A.size(); i++) {
            xr ^= A.get(i);
            int x = xr ^ B;
            cnt += map.getOrDefault(x, 0);
            map.put(xr, map.getOrDefault(xr, 0) + 1);
        }
        return cnt;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
