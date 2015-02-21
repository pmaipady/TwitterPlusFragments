package com.codepath.apps.mysimpletweets;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.mysimpletweets.R;
import com.codepath.apps.mysimpletweets.models.User;
import com.squareup.picasso.Picasso;

public class ComposeTweetActivity extends ActionBarActivity {

    public EditText etCompose;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose_tweet);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra(TimelineActivity.KEY_FOR_EXTRA_USER);

        ImageView ivComposeProfile = (ImageView) findViewById(R.id.ivComposeProfile);
        TextView tvComposeUser = (TextView) findViewById(R.id.tvComposeUser);

        etCompose = (EditText) findViewById(R.id.etCompose);



        Picasso.with(this).load(user.getProfileImageUrl()).into(ivComposeProfile);
        tvComposeUser.setText(user.getScreenName());

    }

    public void onTweet(View v) {
        Intent i = new Intent();
       String str = etCompose.getText().toString();
        i.putExtra("message", str);
        setResult(0, i);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_compose_tweet, menu);
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
