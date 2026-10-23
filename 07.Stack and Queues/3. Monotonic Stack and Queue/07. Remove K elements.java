/*
QUESTION:
Given string num representing a non-negative integer num, and an integer k,
return the smallest possible integer after removing k digits from num.

Example:
Input: num = "1432219", k = 3
Output: "1219"
Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
*/

import java.util.*;
import java.io.*;

// CODE:-
import java.util.Stack;

class Solution {
    public String removeKdig(String s, int k) {
        Stack<Integer> st = new Stack<>();
        for(int i=0;i<s.length();i++){
            int ch = (s.charAt(i))-'0';
            while(k!=0 && !st.isEmpty() && st.peek()>ch){
                st.pop(); k--; if(k==0) break;
            }
            if(st.isEmpty() && ch==0) continue;
            else st.push(ch);
        }
        while(!st.isEmpty() && k!=0){ st.pop(); k--; }
        StringBuilder str = new StringBuilder();
        if(st.isEmpty()) return str.append('0').toString();
        while(!st.isEmpty()) str.append((char)(st.pop()+'0'));
        return str.reverse().toString();
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
