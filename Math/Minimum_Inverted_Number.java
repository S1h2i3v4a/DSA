/*
 * Problem Name: Minimum Inverted Number (ChewbaCCA and Number)
 * Platform: GeeksforGeeks / Codeforces
 * Difficulty: Easy
 * Time Complexity: O(log10 N)
 * Space Complexity: O(1)
 */

import java.util.Scanner;

public class Solution {
     public static void main(String[] args){
         Scanner sc = new Scanner(System.in);
         int x = sc.nextInt();
         int y = minNumber(x);
         System.out.print(y);
     }
     public static int minNumber(int x){
         int a=0;
         while(x!=0){
             int y = x%10;
             if(y>9-y) y=9-y;
             a=a*10+y;
             x=x/10;
         }
         int l = reverse(a);
         return l;
     }
     public static int reverse(int number){
           int reversed = 0;

        while (number != 0) {
            // 1. Get the last digit
            int digit = number % 10; 
            
            // 2. Append the digit to the reversed number
            reversed = reversed * 10 + digit; 
            
            // 3. Remove the last digit from the original number
            number = number / 10; 
        }
        return reversed;
     }
}
