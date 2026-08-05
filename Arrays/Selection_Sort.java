/*
 * Problem Name: Selection Sort
 * Platform: GeeksforGeeks
 * Difficulty: Easy
 * Time Complexity: O(N^2)
 * Space Complexity: O(1)
 */

class Solution {
    void selectionSort(int[] arr) {
        // code here
        for(int i=0;i<arr.length-1;i++){
            for(int j=i+1;j<arr.length;j++){
                if(arr[i]>arr[j]){
                    int temp = arr[i];
                    arr[i]=arr[j];
                    arr[j] = temp;
                }
            }
        }
    }
}
