/*
 * Problem Name: Frequencies of Limited Range Array Elements
 * Platform: GeeksforGeeks
 * Difficulty: Easy
 * Time Complexity: O(N)
 * Space Complexity: O(N)
 */

import java.util.ArrayList;
import java.util.List;

class Solution {
    // Function to count the frequency of all elements from 1 to N in the array.
    public List<Integer> frequencyCount(int[] arr) {
        List<Integer> result = new ArrayList<>();
        int[] count = new int[arr.length];
        for(int i=0;i<arr.length;i++){
            count[arr[i]-1]++;
        }
        for(int i=0;i<arr.length;i++){
            result.add(i,count[i]);
        }
        return result;
    }
}
