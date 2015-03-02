package com.codepath.apps.twitterplusfragments.fragments;

import android.os.Bundle;
import android.util.Log;

import com.codepath.apps.twitterplusfragments.TwitterApplication;
import com.codepath.apps.twitterplusfragments.TwitterClient;
import com.codepath.apps.twitterplusfragments.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;

/**
 * Created by Prit on 2/27/2015.
 */
public class MentionsTimelineFragment extends TweetsListFragment {
    private TwitterClient client;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        client = TwitterApplication.getRestClient();
        populateTimeline();
    }

    private void populateTimeline() {
        client.getMentionsTimeline(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray json) {
                Log.d("DEBUG", json.toString());
                addAll(Tweet.fromJSONArray(json));
                //Log.d("DEBUG", aTweets.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d("DEBUG", responseString);
            }
        });
    }

}
