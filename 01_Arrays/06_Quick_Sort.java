/*
 * Problem Name: Quick Sort
 * Platform: GeeksforGeeks
 * Difficulty: Medium
 * Time Complexity: O(N log N)
 * Space Complexity: O(log N)
 */

class Solution {
    static void quickSort(int arr[], int low, int high) {
        if(low>=high){
            return;
        }
        int pivot = partition(arr,low,high);
        quickSort(arr,low,pivot-1);
        quickSort(arr,pivot+1,high);
    }

    static int partition(int arr[], int low, int high) {
        int pivot = arr[high];
        int i=low;
        int j=low;
        while(i<=high){
            if(arr[i]<=pivot){
                int temp=arr[i];
                arr[i]=arr[j];
                arr[j]=temp;
                j++;
            }
            i++;
        }
        return j-1;
    }
}
