/*
I don't think anyone needs it's solution. The idea is to traverse the array using loop and when the element
is equal to k return the same
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public static int search(int arr[], int n, int x) {
        for (int i = 0; i < n; i++) {
            if (arr[i] == x)
                return i;
        }
        return -1;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
