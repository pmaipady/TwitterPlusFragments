package com.codepath.apps.twitterplusfragments.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Prit on 2/19/2015.
 */
public class User implements Serializable {
    private String name;
    private long uid;
    private String screenName;
    private String profileImageUrl;
    private String tagLine;
    private int followersCount;
    private int friendsCount;

    public String getName() {
        return name;
    }

    public long getUid() {
        return uid;
    }

    public String getScreenName() {
        return screenName;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public int getFollowersCount() {
        return followersCount;
    }

    public int getFriendsCount() {
        return friendsCount;
    }

    public String getTagLine() {

        return tagLine;
    }

    public static User fromJSON(JSONObject json){

        User u = new User();

        try {
            u.name= json.getString("name");
            u.uid=json.getLong("id");
            u.screenName=json.getString("screen_name");
            u.profileImageUrl=json.getString("profile_image_url");
            u.tagLine=json.getString("description");
            u.followersCount=json.getInt("followers_count");
            u.friendsCount=json.getInt("friends_count");


        } catch (JSONException e) {
            e.printStackTrace();
        }


        return u;
    }
}
