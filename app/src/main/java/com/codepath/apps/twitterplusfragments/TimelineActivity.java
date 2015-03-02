package com.codepath.apps.twitterplusfragments;

import android.app.Fragment;
//import android.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.astuetz.PagerSlidingTabStrip;
import com.codepath.apps.twitterplusfragments.fragments.HomeTimelineFragment;
import com.codepath.apps.twitterplusfragments.fragments.MentionsTimelineFragment;
import com.codepath.apps.twitterplusfragments.fragments.TweetsListFragment;
import com.codepath.apps.twitterplusfragments.models.ProfileActivity;
import com.codepath.apps.twitterplusfragments.models.Tweet;
import com.codepath.apps.twitterplusfragments.models.User;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;


import java.util.ArrayList;

public class TimelineActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        ViewPager vpPager = (ViewPager)findViewById(R.id.viewpager);
        vpPager.setAdapter(new TweetsPagerAdapter(getSupportFragmentManager()));
        PagerSlidingTabStrip tabStrip = (PagerSlidingTabStrip)findViewById(R.id.tabs);
        tabStrip.setViewPager(vpPager);

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
        return super.onOptionsItemSelected(item);
    }

    public void onProfileView(MenuItem mi){

        Intent i = new Intent(this, ProfileActivity.class);
        startActivity(i);
    }

    public class TweetsPagerAdapter extends FragmentPagerAdapter {
        final int PAGE_COUNT = 2;
        private String tabTitles[] = {"HOME", "MENTIONS"};

        public TweetsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            if(position == 0){
                return new HomeTimelineFragment();
            }else if (position == 1){
                return new MentionsTimelineFragment();
            }else {
                return null;
            }

            }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }

        @Override
        public int getCount() {
            return tabTitles.length;
        }
    }
}