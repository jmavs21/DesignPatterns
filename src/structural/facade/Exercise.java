package structural.facade;

import java.util.ArrayList;
import java.util.List;

public class Exercise {
    public static void main(String[] args) {
        TwitterAPI twitterAPI = new TwitterAPI("appKey", "secret");
        List<Tweet> tweets = twitterAPI.getRecentTweets();
    }
}

class TwitterAPI {
    private String appKey;
    private String secret;
    public TwitterAPI(String appKey, String secret) {
        this.appKey = appKey;
        this.secret = secret;
    }
    public List<Tweet> getRecentTweets() {
        TwitterClient twitterClient = new TwitterClient();
        List<Tweet> tweets = twitterClient.getRecentTweets(getAccessToken());
        return tweets;
    }
    private String getAccessToken() {
        OAuth oauth = new OAuth();
        String requestToken = oauth.requestToken("appKey", "secret");
        String accessToken = oauth.getAccessToken(requestToken);
        return accessToken;
    }
}

class TwitterClient {
    public List<Tweet> getRecentTweets(String accessToken) {
        System.out.println("Getting recent tweets");
        return new ArrayList<>();
    }
}

class OAuth {
    public String requestToken(String appKey, String appSecret) {
        System.out.println("Get a request token");
        return "requestToken";
    }
    public String getAccessToken(String requestToken) {
        System.out.println("Get an access token");
        return "accessToken";
    }
}

class Tweet {}
