/*
 * Problem Name: Merge Sort
 * Platform: GeeksforGeeks
 * Difficulty: Medium
 * Time Complexity: O(N log N)
 * Space Complexity: O(N)
 */

class Solution {
    void merge(int arr[],int low,int mid,int high){
        int i=low;
        int j=mid+1;
        int k = 0;
        int[] result = new int[high-low+1];
        while(i<=mid&&j<=high){
            if(arr[i]>arr[j]){
                result[k]=arr[j];
                j++;
                k++;
            }
            else{
                result[k]=arr[i];
                i++;
                k++;
            }
        }
        while(i<=mid){
            result[k]=arr[i];
            i++;
            k++;
        }
        while(j<=high){
            result[k]=arr[j];
            j++;
            k++;
        }
        for(int m=0;m<result.length;m++){
            arr[low+m]=result[m];
        }
    }

    void mergeSort(int arr[], int l, int r) {
        if(l==r){
            return;
        }
        int mid = (l+r)/2;
        mergeSort(arr,l,mid);
        mergeSort(arr,mid+1,r);
        merge(arr,l,mid,r);
    }
}
