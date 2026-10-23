# 18. Dynamic Programming

## 📌 Overview
Dynamic Programming optimizes recursive solutions by breaking problems into subproblems and caching results to prevent redundant calculations (Memoization, Tabulation, Space-Optimization, and Matrix Exponentiation).

## 📑 Practice Problems
| # | Problem Name | Difficulty | Solution File | Notes |
|---|---|---|---|---|
| 01 | Nth Fibonacci Number | Easy | [01_Nth_Fibonacci_Number.java](./01_Nth_Fibonacci_Number.java) | Memoization, Tabulation, Space-Optimized & Matrix Exp |
| 02 | Geek's Training | Medium | [02_Geeks_Training.java](./02_Geeks_Training.java) | 2D State Transitions with O(1) Space Optimization |
| 03 | Unique Paths | Medium | [03_Unique_Paths.java](./03_Unique_Paths.java) | Grid Top-Down, Bottom-Up, O(N) Space & Combinatorics |
| 04 | Unique Paths II | Medium | [04_Unique_Paths_II.java](./04_Unique_Paths_II.java) | Obstacle Grid Path DP with 1D Rolling Array |
| 05 | Minimum Path Sum | Medium | [05_Minimum_Path_Sum.java](./05_Minimum_Path_Sum.java) | Grid Min-Cost Accumulation & O(N) Space Optimization |
| 06 | Triangle | Medium | [06_Triangle.java](./06_Triangle.java) | Bottom-Up Triangular Grid Reduction & O(N) Space DP |
| 07 | Minimum Falling Path Sum | Medium | [07_Minimum_Falling_Path_Sum.java](./07_Minimum_Falling_Path_Sum.java) | 3-Way Downward Path DP & O(N) Space Optimization |
| 08 | Cherry Pickup II | Hard | [08_Cherry_Pickup_II.java](./08_Cherry_Pickup_II.java) | 3D DP for Dual Simultaneous Robots & O(C^2) Space |
| 09 | Subset Sum Problem | Medium | [09_Subset_Sum_Problem.java](./09_Subset_Sum_Problem.java) | 0-1 Choice Top-Down, Bottom-Up & O(Target) Space |
| 10 | Partition Equal Subset Sum | Medium | [10_Partition_Equal_Subset_Sum.java](./10_Partition_Equal_Subset_Sum.java) | Target Sum Half Reduction & 0-1 Knapsack DP |
| 11 | Partition Array Into Two Arrays to Minimize Sum Difference | Hard | [11_Partition_Array_Into_Two_Arrays_To_Minimize_Sum_Difference.java](./11_Partition_Array_Into_Two_Arrays_To_Minimize_Sum_Difference.java) | GFG Subset DP & Meet-in-the-Middle Binary Search |
| 12 | Count Subsets with Sum K | Medium | [12_Count_Subsets_With_Sum_K.java](./12_Count_Subsets_With_Sum_K.java) | Zero-Handling Base Cases & O(Target) Space DP |
| 13 | Count Partitions with Given Difference | Medium | [13_Count_Partitions_With_Given_Difference.java](./13_Count_Partitions_With_Given_Difference.java) | Algebraic Target Reduction to Subset Sum Count |
| 14 | 0/1 Knapsack | Medium | [14_0_1_Knapsack.java](./14_0_1_Knapsack.java) | Classical 0-1 Bounded Choice & Backward 1D Array DP |
| 15 | Coin Change | Medium | [15_Coin_Change.java](./15_Coin_Change.java) | Unbounded Knapsack Infinite Supply Forward 1D DP |
| 16 | Target Sum | Medium | [16_Target_Sum.java](./16_Target_Sum.java) | Sign Assignment Algebraic Subset Sum Count |
| 17 | Coin Change II | Medium | [17_Coin_Change_II.java](./17_Coin_Change_II.java) | Unbounded Combinations & Forward 1D Space DP |
| 18 | Unbounded Knapsack | Medium | [18_Unbounded_Knapsack.java](./18_Unbounded_Knapsack.java) | Infinite Supply Multiple Selection 1D Forward DP |
| 19 | Rod Cutting | Medium | [19_Rod_Cutting.java](./19_Rod_Cutting.java) | Unbounded Knapsack Piece Length Revenue Maximization |
| 20 | Longest Common Subsequence | Medium | [20_Longest_Common_Subsequence.java](./20_Longest_Common_Subsequence.java) | Prefix Matching 2D Grid & 2-Row Space Optimization |
| 21 | Print Longest Common Subsequence | Medium | [21_Print_Longest_Common_Subsequence.java](./21_Print_Longest_Common_Subsequence.java) | Backtracking Path Reconstruction on LCS Matrix |
| 22 | Longest Common Substring | Medium | [22_Longest_Common_Substring.java](./22_Longest_Common_Substring.java) | Contiguous Substring DP Reset on Mismatch & O(N) Space |
| 23 | Longest Palindromic Subsequence | Medium | [23_Longest_Palindromic_Subsequence.java](./23_Longest_Palindromic_Subsequence.java) | Reduction to LCS with Reverse String & O(N) Space |
| 24 | Minimum Insertions to Make String Palindrome | Hard | [24_Minimum_Insertions_To_Make_String_Palindrome.java](./24_Minimum_Insertions_To_Make_String_Palindrome.java) | String Length Minus Longest Palindromic Subsequence |
| 25 | Delete Operation for Two Strings | Medium | [25_Delete_Operation_For_Two_Strings.java](./25_Delete_Operation_For_Two_Strings.java) | Minimum Deletions/Insertions via (M + N - 2*LCS) |
| 26 | Shortest Common Supersequence | Hard | [26_Shortest_Common_Supersequence.java](./26_Shortest_Common_Supersequence.java) | LCS Backtracking Matrix Traversal & String Assembly |
| 27 | Distinct Subsequences | Hard | [27_Distinct_Subsequences.java](./27_Distinct_Subsequences.java) | 2D Matching Transitions with 1D Backward Optimization |
| 28 | Edit Distance | Hard | [28_Edit_Distance.java](./28_Edit_Distance.java) | Insert, Delete, Replace DP with 2-Row Space Optimization |
| 29 | Wildcard Matching | Hard | [29_Wildcard_Matching.java](./29_Wildcard_Matching.java) | Question Mark and Star Matching with 1D Space DP |
| 30 | Best Time to Buy and Sell Stock II | Medium | [30_Best_Time_To_Buy_And_Sell_Stock_II.java](./30_Best_Time_To_Buy_And_Sell_Stock_II.java) | Infinite Transactions State Machine & O(1) Space DP |
| 31 | Best Time to Buy and Sell Stock III | Hard | [31_Best_Time_To_Buy_And_Sell_Stock_III.java](./31_Best_Time_To_Buy_And_Sell_Stock_III.java) | At Most 2 Transactions 3D DP with O(1) Space |
| 32 | Best Time to Buy and Sell Stock IV | Hard | [32_Best_Time_To_Buy_And_Sell_Stock_IV.java](./32_Best_Time_To_Buy_And_Sell_Stock_IV.java) | At Most K Transactions DP with O(K) Space Optimization |
| 33 | Best Time to Buy and Sell Stock With Cooldown | Medium | [33_Best_Time_To_Buy_And_Sell_Stock_With_Cooldown.java](./33_Best_Time_To_Buy_And_Sell_Stock_With_Cooldown.java) | 1-Day Mandatory Cooling Period with O(1) Space DP |
| 34 | Best Time to Buy and Sell Stock With Transaction Fee | Medium | [34_Best_Time_To_Buy_And_Sell_Stock_With_Transaction_Fee.java](./34_Best_Time_To_Buy_And_Sell_Stock_With_Transaction_Fee.java) | Fee Deduction on Selling with O(1) Space DP |
| 35 | Longest Increasing Subsequence | Medium | [35_Longest_Increasing_Subsequence.java](./35_Longest_Increasing_Subsequence.java) | O(N^2) Tabulation & O(N log N) Patience Sorting Binary Search |
| 36 | Print Longest Increasing Subsequence | Medium | [36_Print_Longest_Increasing_Subsequence.java](./36_Print_Longest_Increasing_Subsequence.java) | Parent Predecessor Pointer Backtracking on LIS |
| 37 | Largest Divisible Subset | Medium | [37_Largest_Divisible_Subset.java](./37_Largest_Divisible_Subset.java) | Array Sorting with Divisibility LIS Parent Tracking |
| 38 | Longest String Chain | Medium | [38_Longest_String_Chain.java](./38_Longest_String_Chain.java) | Length Sorting & Predecessor Substring HashMap DP |
| 39 | Longest Bitonic Subsequence | Medium | [39_Longest_Bitonic_Subsequence.java](./39_Longest_Bitonic_Subsequence.java) | Dual LIS (Left-to-Right & Right-to-Left Peak Fusion) |
| 40 | Number of Longest Increasing Subsequences | Medium | [40_Number_Of_Longest_Increasing_Subsequences.java](./40_Number_Of_Longest_Increasing_Subsequences.java) | Length Tracking & Ways Accumulator Dual DP Arrays |
| 41 | Matrix Chain Multiplication | Hard | [41_Matrix_Chain_Multiplication.java](./41_Matrix_Chain_Multiplication.java) | Classical Partition DP Parenthesization Minimization |
| 42 | Minimum Cost to Cut a Stick | Hard | [42_Minimum_Cost_To_Cut_A_Stick.java](./42_Minimum_Cost_To_Cut_A_Stick.java) | Boundary Padding & Partition DP on Ordered Cut Indices |
| 43 | Burst Balloons | Hard | [43_Burst_Balloons.java](./43_Burst_Balloons.java) | Reverse Last Balloon Burst Partition DP |
| 44 | Evaluate Boolean Expression to True | Hard | [44_Evaluate_Boolean_Expression_To_True.java](./44_Evaluate_Boolean_Expression_To_True.java) | 3D Boolean Parenthesization Partition DP |
| 45 | Palindrome Partitioning II | Hard | [45_Palindrome_Partitioning_II.java](./45_Palindrome_Partitioning_II.java) | O(N^2) Palindrome Precomputation & Front Partition DP |
| 46 | Partition Array for Maximum Sum | Medium | [46_Partition_Array_For_Maximum_Sum.java](./46_Partition_Array_For_Maximum_Sum.java) | Front Partitioning with Sliding Window Maximum |
| 47 | Maximum Rectangle Area with All 1's | Hard | [47_Maximum_Rectangle_Area_With_All_1s.java](./47_Maximum_Rectangle_Area_With_All_1s.java) | Row Histogram Accumulation & Monotonic Stack DP |
| 48 | Count Square Submatrices with All Ones | Medium | [48_Count_Square_Submatrices_With_All_1s.java](./48_Count_Square_Submatrices_With_All_1s.java) | Bottom-Right Corner 2D Square Extension DP |
