/*
QUESTION:
Design a simplified version of Twitter where users can post tweets, follow/unfollow another user,
and is able to see the 10 most recent tweets in the user's news feed.

APPROACH:
- Store all tweets in a central list with `(timestamp, userId, tweetId)`.
- Follow relationships in `Map<Integer, Set<Integer>>`.
- On `getNewsFeed(userId)`:
  - Filter all tweets posted by `userId` or anyone `userId` follows.
  - Sort filtered tweets by timestamp descending.
  - Take the first 10.

TIME COMPLEXITY:
- postTweet: O(1)
- follow/unfollow: O(1)
- getNewsFeed: O(T log T) where T is total number of tweets.
SPACE COMPLEXITY: O(T + F) where T is tweets and F is follow relations.
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Solution {
    private static class Tweet {
        int id, userId, time;
        Tweet(int id, int userId, int time) {
            this.id = id;
            this.userId = userId;
            this.time = time;
        }
    }

    private List<Tweet> allTweets;
    private Map<Integer, Set<Integer>> following;
    private int timeStamp;

    public Solution() {
        allTweets = new ArrayList<>();
        following = new HashMap<>();
        timeStamp = 0;
    }

    public void postTweet(int userId, int tweetId) {
        allTweets.add(new Tweet(tweetId, userId, timeStamp++));
    }

    public List<Integer> getNewsFeed(int userId) {
        Set<Integer> followees = following.getOrDefault(userId, new HashSet<>());
        List<Tweet> feed = new ArrayList<>();

        for (Tweet t : allTweets) {
            if (t.userId == userId || followees.contains(t.userId)) {
                feed.add(t);
            }
        }

        feed.sort((a, b) -> b.time - a.time);
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < Math.min(10, feed.size()); i++) {
            result.add(feed.get(i).id);
        }
        return result;
    }

    public void follow(int followerId, int followeeId) {
        following.computeIfAbsent(followerId, k -> new HashSet<>()).add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        if (following.containsKey(followerId)) {
            following.get(followerId).remove(followeeId);
        }
    }
}
