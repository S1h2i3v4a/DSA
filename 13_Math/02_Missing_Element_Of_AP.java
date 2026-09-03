/*
 * Problem Name: Missing Element of AP
 * Platform: GeeksforGeeks
 * Difficulty: Easy
 * Time Complexity: O(N)
 * Space Complexity: O(1)
 */

class Solution {
    int findMissing(int[] arr) {
        int size = arr.length;
        int d = (arr[size-1]-arr[0])/size, i = 0;
        while(i<arr.length-1){
            if(arr[i+1]-arr[i]==d) i++;
            else return arr[i]+d;
        }
        return -1;
    }
}
