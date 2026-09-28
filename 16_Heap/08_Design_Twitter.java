/**
 * Problem Name: Design Twitter
 * Platform: LeetCode (LC 355)
 * Difficulty: Medium
 * 
 * Time Complexity:
 *   - postTweet: O(1)
 *   - follow / unfollow: O(1)
 *   - getNewsFeed: O(F * log K) where F is the number of followees and K = 10
 * Space Complexity: O(U + T) where U is total users/follow relations and T is total tweets
 * 
 * Approach:
 * Maintain a global timestamp for ordering tweets.
 * Use a Map of userId to Set of followees, and a Map of userId to List of Tweet objects.
 * To retrieve the top 10 news feed items, merge the tweet lists of the user and all their followees
 * using a PriorityQueue (Min-Heap of max size 10 or Max-Heap of most recent tweets).
 */

import java.util.*;

class Design_Twitter {
    private static int timeStamp = 0;

    private static class Tweet {
        int id;
        int time;

        Tweet(int id, int time) {
            this.id = id;
            this.time = time;
        }
    }

    private Map<Integer, Set<Integer>> follows;
    private Map<Integer, List<Tweet>> tweets;

    public Design_Twitter() {
        follows = new HashMap<>();
        tweets = new HashMap<>();
    }

    public void postTweet(int userId, int tweetId) {
        tweets.putIfAbsent(userId, new ArrayList<>());
        tweets.get(userId).add(new Tweet(tweetId, ++timeStamp));
    }

    public List<Integer> getNewsFeed(int userId) {
        PriorityQueue<Tweet> minHeap = new PriorityQueue<>((a, b) -> a.time - b.time);
        
        Set<Integer> userFollows = new HashSet<>(follows.getOrDefault(userId, new HashSet<>()));
        userFollows.add(userId); // Self tweets included

        for (int followeeId : userFollows) {
            List<Tweet> userTweets = tweets.get(followeeId);
            if (userTweets != null) {
                for (int i = userTweets.size() - 1; i >= 0 && i >= userTweets.size() - 10; i--) {
                    minHeap.add(userTweets.get(i));
                    if (minHeap.size() > 10) {
                        minHeap.poll();
                    }
                }
            }
        }

        List<Integer> result = new LinkedList<>();
        while (!minHeap.isEmpty()) {
            result.add(0, minHeap.poll().id);
        }
        return result;
    }

    public void follow(int followerId, int followeeId) {
        if (followerId == followeeId) return;
        follows.putIfAbsent(followerId, new HashSet<>());
        follows.get(followerId).add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        if (follows.containsKey(followerId)) {
            follows.get(followerId).remove(followeeId);
        }
    }

    public static void main(String[] args) {
        Design_Twitter twitter = new Design_Twitter();
        twitter.postTweet(1, 5);
        System.out.println("Feed for 1: " + twitter.getNewsFeed(1)); // [5]
        twitter.follow(1, 2);
        twitter.postTweet(2, 6);
        System.out.println("Feed for 1: " + twitter.getNewsFeed(1)); // [6, 5]
        twitter.unfollow(1, 2);
        System.out.println("Feed for 1: " + twitter.getNewsFeed(1)); // [5]
    }
}
