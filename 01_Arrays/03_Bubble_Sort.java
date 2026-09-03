/*
 * Problem Name: Bubble Sort
 * Platform: GeeksforGeeks
 * Difficulty: Easy
 * Time Complexity: O(N^2)
 * Space Complexity: O(1)
 */

class Solution {
    public void bubbleSort(int[] arr) {
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr.length-i-1;j++){
                if(arr[j]>arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }
}
