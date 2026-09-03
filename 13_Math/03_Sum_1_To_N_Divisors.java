/*
 * Problem Name: Sum 1 to N Divisors
 * Platform: GeeksforGeeks
 * Difficulty: Easy
 * Time Complexity: O(N^2)
 * Space Complexity: O(1)
 */

class Solution {
    public static int sumOfDivisors(int n) {
        int s=0;
        for(int i=0;i<=n;i++){
            int k=0;
            for(int j=1;j<=i;j++){
                if(i%j==0) k=k+j;
            }
            s=s+k;
        }
        return s;
    }
}
