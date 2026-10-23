/*Question:-

Count the number of undirected graphs that can be formed with n vertices.

Approach:-
- calculate the number of edges with formula n*n-1/2
- return pow(2,edges) 

Complexity Analysis:-

Time Complexity = O(1)
Space Complexity = O(1)
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    static long count(int n) {
        long edges = (long) n * (n - 1) / 2;
        return 1L << edges;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
