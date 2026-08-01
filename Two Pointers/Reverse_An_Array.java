/*
 * Problem Name: Reverse an Array
 * Platform: GeeksforGeeks
 * Difficulty: Easy
 * Time Complexity: O(N)
 * Space Complexity: O(1)
 */

class Solution {
    public void reverseArray(int arr[]) {
        int n = arr.length;
        int k = n;
        for(int i=0;i<n/2;i++){
            k=k-1;
            int temp = arr[i];
            arr[i]=arr[k];
            arr[k]=temp;
        }
    }
}
