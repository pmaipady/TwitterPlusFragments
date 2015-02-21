package com.codepath.apps.mysimpletweets;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.codepath.apps.mysimpletweets.R;
import com.codepath.apps.mysimpletweets.models.Tweet;
import com.codepath.apps.mysimpletweets.models.User;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;


import java.util.ArrayList;

public class TimelineActivity extends ActionBarActivity {

    public static final String KEY_FOR_EXTRA_USER = "user";
    private final int REQUEST_CODE = 20;
    private final int RESULT_OK = 0;
    private TwitterClient client;
    private ArrayList tweets;
    private TweetsArrayAdapter aTweets;
    private ListView lvTweets;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        lvTweets=(ListView)findViewById(R.id.lvTweets);
        tweets= new ArrayList();
        aTweets= new TweetsArrayAdapter(this,tweets);
        lvTweets.setAdapter(aTweets);
        client=TwitterApplication.getRestClient();
        populateTimeline();
        populateUserdata();

    }


    private void populateTimeline(){
        client.getHomeTimeline(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray json) {
                Log.d("DEBUG", json.toString());
                aTweets.addAll(Tweet.fromJSONArray(json));
                Log.d("DEBUG",aTweets.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d("DEBUG", responseString);
            }
        });
    }
    private void populateUserdata(){

        client.getUserData(new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject json) {
                user = user.fromJSON(json);


                Log.d("DEBUG", json.toString());

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d("DEBUG", responseString);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_timeline, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_compose) {

            Intent intent = new Intent(this, ComposeTweetActivity.class);

            intent.putExtra(KEY_FOR_EXTRA_USER,  user);
            startActivityForResult(intent, REQUEST_CODE);
            return true;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
// REQUEST_CODE is defined above
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
// Extract name value from result extras

            String name = data.getExtras().getString("message");
            JsonHttpResponseHandler handler = new JsonHttpResponseHandler();
// post tweet

            client.postTweet(name,handler);
            this.recreate();

        }
    }

}
