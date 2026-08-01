/*
 * Problem Name: Factorial Numbers Less Than or Equal to N
 * Platform: GeeksforGeeks
 * Difficulty: Easy
 * Time Complexity: O(K)
 * Space Complexity: O(1)
 */

import java.util.ArrayList;

class Solution {
    static ArrayList<Long> factorialNumbers(long n) {
        long s=1;
        ArrayList<Long> arr = new ArrayList<>();
        if(n==0){
            arr.add(s);
            return arr;
        }
        long t=1;
        while(s<=n){
            long k=1;
            for(long i=1;i<=t;i++){
                k=k*i;
            }
            if(k>n){
                break;
            }
            s=k;
            arr.add(s);
            t++;
        }
        return arr;
    }
}
