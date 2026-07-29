/*
 * Problem Name: LCM And GCD
 * Platform: GeeksforGeeks
 * Difficulty: Basic
 * Time Complexity: O(log(min(a, b)))
 * Space Complexity: O(1)
 */

class Solution {
    public static int[] lcmAndGcd(int a, int b) {
        int[] arr = new int[2];
        int k=Math.max(a,b);
        int j=Math.max(a,b);
        int i=1;
        int t=Math.min(a,b);
        while(j%t!=0){
            j=k*i;
            i++;
        }
        arr[0]=j;
        while(t!=0){
            int temp=t;
            t=k%t;
            k=temp;
        }
        arr[1]=k;
        return arr;
    }
}
