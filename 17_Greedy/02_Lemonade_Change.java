/**
 * Problem Name: Lemonade Change
 * Platform: LeetCode (LC 860)
 * Difficulty: Easy
 * 
 * Time Complexity: O(N) where N is the number of customers
 * Space Complexity: O(1) using greedy bill tracking variables
 * 
 * Approach:
 * Track available $5 and $10 bills.
 * - Customer pays $5: increment $5 count.
 * - Customer pays $10: needs $5 change -> decrement $5, increment $10.
 * - Customer pays $20: needs $15 change -> Greedy choice: prefer ($10 + $5) to save flexible $5 bills.
 *   If no $10 available, fallback to three $5 bills.
 * If change cannot be provided at any step, return false.
 */

class Lemonade_Change {
    public boolean lemonadeChange(int[] bills) {
        int five = 0;
        int ten = 0;

        for (int bill : bills) {
            if (bill == 5) {
                five++;
            } else if (bill == 10) {
                if (five == 0) return false;
                five--;
                ten++;
            } else { // bill == 20
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

    public static void main(String[] args) {
        Lemonade_Change solver = new Lemonade_Change();
        int[] bills1 = {5, 5, 5, 10, 20};
        System.out.println("Can provide change [5, 5, 5, 10, 20]: " + solver.lemonadeChange(bills1)); // true

        int[] bills2 = {5, 5, 10, 10, 20};
        System.out.println("Can provide change [5, 5, 10, 10, 20]: " + solver.lemonadeChange(bills2)); // false
    }
}
