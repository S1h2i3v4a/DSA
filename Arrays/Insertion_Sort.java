/*
 * Problem Name: Insertion Sort
 * Platform: GeeksforGeeks
 * Difficulty: Easy
 * Time Complexity: O(N^2)
 * Space Complexity: O(1)
 */

class Solution {
    // Please change the array in-place
    public void insertionSort(int arr[]) {
        for(int i=0;i<arr.length-1;i++){
            int key = arr[i+1];
            int j=i;
            while(j>=0 && arr[j]>=key){
                int temp=arr[j];
                arr[j+1]=temp;
                j--;
            }
            arr[j+1]=key;
        }
    }
}
