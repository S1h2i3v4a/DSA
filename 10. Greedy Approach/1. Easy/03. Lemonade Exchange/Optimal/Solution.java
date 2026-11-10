/*
QUESTION:
At a lemonade stand, each lemonade costs $5. Customers pay with $5, $10, or $20 bills.
Return true if you can provide every customer with correct change, or false otherwise.

APPROACH:
- Track counts of $5 and $10 bills (we never need to give $20 as change).
- For each bill:
  - If $5: `five++`.
  - If $10: need $5 change -> if `five > 0`, `five--`, `ten++`; else return false.
  - If $20: need $15 change -> greedily give one $10 and one $5 if available; else give three $5 bills; else return false.

TIME COMPLEXITY: O(N) single pass through bills.
SPACE COMPLEXITY: O(1) constant auxiliary space.
*/

class Solution {
    public boolean lemonadeChange(int[] bills) {
        int five = 0, ten = 0;

        for (int bill : bills) {
            if (bill == 5) {
                five++;
            } else if (bill == 10) {
                if (five == 0) return false;
                five--;
                ten++;
            } else {
                if (ten > 0 && five > 0) {
                    ten--;
                    five--;
                } else if (five >= 3) {
                    five -= 3;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
