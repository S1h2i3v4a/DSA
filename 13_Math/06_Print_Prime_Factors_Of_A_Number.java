/**
 * Problem Name: Print Prime Factors of a Number
 * Platform: GeeksforGeeks
 * Difficulty: Easy
 * 
 * Time Complexity: O(sqrt(N))
 * Space Complexity: O(1) auxiliary space.
 * 
 * Approach:
 * Trial Division.
 * 1. Check factor 2: divide N by 2 until N is odd.
 * 2. Check odd factors from 3 up to sqrt(N) with step 2: divide N out completely.
 * 3. If remaining N > 2, N itself is a prime factor.
 */

import java.util.ArrayList;
import java.util.List;

class Print_Prime_Factors_Of_A_Number {

    public static List<Integer> primeFactors(int n) {
        List<Integer> factors = new ArrayList<>();

        if (n % 2 == 0) {
            factors.add(2);
            while (n % 2 == 0) {
                n /= 2;
            }
        }

        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) {
                factors.add(i);
                while (n % i == 0) {
                    n /= i;
                }
            }
        }

        if (n > 2) {
            factors.add(n);
        }

        return factors;
    }

    public static void main(String[] args) {
        int n = 60;
        System.out.println("Prime factors of " + n + ": " + primeFactors(n));
    }
}
