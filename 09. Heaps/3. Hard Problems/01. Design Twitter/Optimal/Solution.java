/*
QUESTION:
Design Twitter using a Max-Heap (PriorityQueue) for optimal news feed retrieval.

APPROACH:
- Each user has a linked list of their own tweets (newest head).
- Follow relationships stored in `Map<Integer, Set<Integer>>`.
- `getNewsFeed(userId)`:
  - Collect the head tweet of `userId` and all users they follow.
  - Insert them into a Max-Heap based on timestamp.
  - Poll the most recent tweet up to 10 times, pushing the next tweet in that user's list into the heap.

TIME COMPLEXITY:
- postTweet, follow, unfollow: O(1)
- getNewsFeed: O(F log F + 10 log F) where F is the number of followees.
SPACE COMPLEXITY: O(U + T) where U is users and T is tweets.
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

class Solution {
    private static int timeStamp = 0;

    private static class Tweet {
        int id;
        int time;
        Tweet next;

        Tweet(int id) {
            this.id = id;
            this.time = timeStamp++;
            this.next = null;
        }
    }

    private Map<Integer, Set<Integer>> userFollows;
    private Map<Integer, Tweet> userTweets;

    public Solution() {
        userFollows = new HashMap<>();
        userTweets = new HashMap<>();
    }

    public void postTweet(int userId, int tweetId) {
        Tweet newTweet = new Tweet(tweetId);
        newTweet.next = userTweets.get(userId);
        userTweets.put(userId, newTweet);
    }

    public List<Integer> getNewsFeed(int userId) {
        List<Integer> feed = new ArrayList<>();
        PriorityQueue<Tweet> maxHeap = new PriorityQueue<>((a, b) -> b.time - a.time);

        Set<Integer> followees = new HashSet<>(userFollows.getOrDefault(userId, new HashSet<>()));
        followees.add(userId);

        for (int uid : followees) {
            Tweet t = userTweets.get(uid);
            if (t != null) maxHeap.offer(t);
        }

        int count = 0;
        while (!maxHeap.isEmpty() && count < 10) {
            Tweet top = maxHeap.poll();
            feed.add(top.id);
            count++;
            if (top.next != null) {
                maxHeap.offer(top.next);
            }
        }
        return feed;
    }

    public void follow(int followerId, int followeeId) {
        userFollows.computeIfAbsent(followerId, k -> new HashSet<>()).add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        if (userFollows.containsKey(followerId) && followerId != followeeId) {
            userFollows.get(followerId).remove(followeeId);
        }
    }
}
