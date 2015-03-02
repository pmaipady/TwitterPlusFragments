package com.codepath.apps.twitterplusfragments.models;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.twitterplusfragments.R;
import com.codepath.apps.twitterplusfragments.TwitterApplication;
import com.codepath.apps.twitterplusfragments.TwitterClient;
import com.codepath.apps.twitterplusfragments.fragments.UserTimelineFragment;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

public class ProfileActivity extends ActionBarActivity {
    TwitterClient client;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        client= TwitterApplication.getRestClient();
        client.getUserInfo(new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                user = User.fromJSON(response);
                getSupportActionBar().setTitle(""+ user.getScreenName());
                populateProfileHeader(user);
            }
        });
        String screenName = getIntent().getStringExtra("screen_name");
        if(savedInstanceState == null) {
            UserTimelineFragment fragmentUserTumeline = UserTimelineFragment.newInstance(screenName);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.flContainer, fragmentUserTumeline);
            ft.commit();
        }

    }


    private void populateProfileHeader(User user){
        ImageView ivComposeProfile = (ImageView) findViewById(R.id.ivComposeProfile);
           TextView tvComposeUser = (TextView) findViewById(R.id.tvComposeUser);
        TextView tvFollowersCount = (TextView) findViewById(R.id.tvFollowersCount);
        TextView tvFriendsCount = (TextView) findViewById(R.id.tvFriendsCount);
        TextView tvTagLine = (TextView) findViewById(R.id.tvTagline);
           Picasso.with(this).load(user.getProfileImageUrl()).into(ivComposeProfile);
           tvComposeUser.setText(user.getScreenName());
        tvFollowersCount.setText("Followers\t\n" + user.getFollowersCount());
        tvFriendsCount.setText("Following\n" + user.getFriendsCount());
        tvTagLine.setText(user.getTagLine());

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
