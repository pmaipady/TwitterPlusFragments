package com.codepath.apps.mysimpletweets.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Prit on 2/19/2015.
 */
public class Tweet {

    private String body;
    private long uid;
    private User user;
    private String createdAt;

    public String getBody() {
        return body;
    }

    public long getUid() {
        return uid;
    }

    public User getUser() {
        return user;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public static Tweet fromJSON(JSONObject jsonObject){
        Tweet tweet = new Tweet();

        try {
            tweet.body= jsonObject.getString("text");
            tweet.uid = jsonObject.getLong("id");
            tweet.user= User.fromJSON(jsonObject.getJSONObject("user"));
            tweet.createdAt = jsonObject.getString("created_at");
        } catch (JSONException e) {
            e.printStackTrace();
        }



        return tweet;

    }
    public static ArrayList<Tweet> fromJSONArray(JSONArray jsonArray){
        ArrayList<Tweet> tweets = new ArrayList<>();
        for (int i=0; i<jsonArray.length(); i++){
            JSONObject tweetjson = null;
            try {
                tweetjson = jsonArray.getJSONObject(i);
                Tweet tweet= Tweet.fromJSON(tweetjson);
                if (tweet!= null) {
                    tweets.add(tweet);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                continue;
            }


        }
        return tweets;
    }
}
