/*
 * Problem Name: Remove K Digits
 * Platform: GeeksforGeeks / LeetCode
 * Difficulty: Medium
 * Time Complexity: O(N)
 * Space Complexity: O(N)
 */

import java.util.Stack;

class Solution {
    public String removeKdig(String s, int k) {
        // code here
        Stack<Integer> st = new Stack<>();
        int i=0;
        for(i=0;i<s.length();i++){
            int ch = (s.charAt(i))-'0';
            while(k!=0 && !st.isEmpty() && st.peek()>ch){
                st.pop();
                k--;
                if(k==0) break;
            }
            if(st.isEmpty() && ch==0) continue;
            else st.push(ch);
        }
        while(!st.isEmpty() && k!=0){
            st.pop();
            k--;
        }
        StringBuilder str = new StringBuilder();
        if(st.isEmpty()) return str.append('0').toString();
        while(!st.isEmpty()) str.append((char)(st.pop()+'0'));
        return str.reverse().toString();
    }
}
