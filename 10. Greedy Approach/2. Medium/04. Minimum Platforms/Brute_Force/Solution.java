/*
QUESTION:
Given arrival and departure times of all trains that reach a railway station,
find the minimum number of platforms required for the railway station so that no train is kept waiting.

APPROACH:
- For each train `i`, count how many trains `j` have overlapping time intervals `[arr[j], dep[j]]` with `[arr[i], dep[i]]`.
- The maximum overlapping count across all trains is the minimum number of platforms needed.

TIME COMPLEXITY: O(N^2) pairwise interval overlap checks.
SPACE COMPLEXITY: O(1) constant auxiliary space.
*/

class Solution {
    public int findPlatform(int[] arr, int[] dep, int n) {
        int maxPlatforms = 1;

        for (int i = 0; i < n; i++) {
            int count = 1;
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    if (arr[i] >= arr[j] && arr[i] <= dep[j]) {
                        count++;
                    }
                }
            }
            maxPlatforms = Math.max(maxPlatforms, count);
        }
        return maxPlatforms;
    }
}
